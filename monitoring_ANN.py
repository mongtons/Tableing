#%%
from datetime import datetime, timedelta
import pymysql
from time import localtime, sleep
import pandas as pd
import numpy as np
from tensorflow.keras import models
from tensorflow.keras.preprocessing.sequence import pad_sequences

path = 'G:/내 드라이브/M2TECH/2022/code/'
#%%
class DB_serch:
    def __init__(self):
        self.url = ""
        self.id = ''
        self.password = ''
        self.dbName = ''
        self.five_sec = timedelta(seconds= 5)
        self.model = models.load_model(path +'Propose_ANN_model.h5')

    def creat_connet(self):
        self.db = pymysql.connect(host=self.url, port=3306, user=self.id, passwd=self.password, db=self.dbName, charset='utf8')
        self.cursor = self.db.cursor()

    def serach_data(self):
        # 실시간 모니터링시 사용 
        now = datetime.now()
        last = now-self.five_sec
        real_time = now.strftime("%Y-%m-%d %H:%M:%S")
        last_time = last.strftime("%Y-%m-%d %H:%M:%S"[:0])
        sql = "select * from beacon_scan_tb where time between'"+str(last_time)+"' and '"+str(real_time)+"' "
        #과거 데이터를 이용한 구현부
        #sql = "select * from beacon_scan_tb where time between '2022-08-16 22:12:27' and '2022-08-16 22:12:32'"
        self.cursor.execute(sql)
        rows = self.cursor.fetchall()
        rows = pd.DataFrame(rows,columns=['minor' , 'rssi' , 'time'])
        rows = rows.sort_values(by=['minor','time'],ascending=[True , False])
        sort_data = rows.reset_index(drop=True)
        sort_data = np.array(sort_data)
        rows = rows.drop_duplicates(['minor'])
        member = rows['minor'].values
        #[0] minor [1] rssi [2]time
        return sort_data , member
    
    def data_preprocessing(self , data_frame):
        input_list = []
        lt = []
    
        index = data_frame[0][0]
        for row in data_frame:
            if index != row[0]:
                input_list.append(lt)
                index = row[0]
                lt = []
                lt.append(row[1])
            else:
                if len(lt) <= 5:
                    lt.append(row[1])
        input_list.append(lt)
        input_list = np.array(input_list)
        input_list = pad_sequences(input_list , padding='post' , maxlen=5)
        ###################
        # fir filter code # 
        ###################        
        for kk in range(len(input_list)):
            for k in range(len(input_list[kk])):
                if k != 0:
                    if input_list[kk][k] != 0:
                        input_list[kk][k] = int(input_list[kk][k] * 0.5 +input_list[kk][k-1] * 0.5 )

        
        return input_list

    def data_predict(self , input_data , member_list):
        np.set_printoptions(precision=4)
        y_pred = self.model.predict(input_data, batch_size=5)

        output = []
        y_pred = np.array(y_pred).tolist()
        y_pred = sum(y_pred , [])

        for kk in range( 0 , len(y_pred)):
            output.append( [ round(y_pred[kk] , 4) , member_list[kk]])


        output = np.array(output)
        return output
               
    def output_data(self , pred_data , std_est):
        #print(pred_data)
        near_dist = np.min(pred_data) 
        dist_list = pred_data[:,0]      #dist_list has only dist info
        #print(near_dist)
        in_area = np.where(dist_list <= std_est , 1 , np.NaN )
        in_area = in_area[~np.isnan(in_area)]

        return float(near_dist) ,len(in_area)
    
    def write_db(self , table , near_dist , in_area):
        sql = "INSERT INTO `" + table + "`(`near_dist`, `in_area`, `time`) \
        VALUES ('" + str(near_dist) +"','"+ str(in_area) +"',CURRENT_TIMESTAMP)"
        self.cursor.execute(sql) 
        self.db.commit()    
    
#%%

##########################################
#               MAIN >_<                 #
##########################################

conn = DB_serch()

try:
    conn.creat_connet()
except:
    print('DB CONNECT ERROR')
while(True):
    try:
        data_tb, member = conn.serach_data()
        input_data = conn.data_preprocessing(data_tb)
        final_data = conn.data_predict(input_data , member)
        near_dist , in_area = conn.output_data(final_data , 3)
        #conn.write_db("output_cal" , near_dist , in_area )
    except:
        sleep(1)
    sleep(0.5)
        

# %%
