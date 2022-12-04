package kr.hallym.Table_counting.controller;

import kr.hallym.Table_counting.domain.entity.*;
import kr.hallym.Table_counting.domain.repository.Output_CalRepository;
import kr.hallym.Table_counting.domain.repository.TableingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private Output_CalRepository opcr;
    @Autowired
    private TableingRepository tr;
    @GetMapping("/BookCafe")
    public String greenRoomIndex(Model model){
        int firstTable=1;
        int secondTable=2;
        int thirdTable=3;
        int fourthTable=4;

        List<Tableing> tableAll=tr.findAll();
        Tableing LastInput = new Tableing();
        for(Tableing table:tableAll)
            LastInput=table;
        Integer key=LastInput.getPk().getKey_value();

        Date nowDate=new Date();

        Date startTime=new Date();
        Date endTime=new Date();

        startTime.setMinutes(endTime.getMinutes()-3);
        startTime.setSeconds(endTime.getSeconds()-30);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Tableing> tableEntity=tr.findByTimeBetween(sdf.format(startTime), sdf.format(endTime));
        boolean[] flag={false, false, false, false};
        Tableing firstTableEntity=null;
        Tableing secondTableEntity=null;
        Tableing thirdTableEntity=null;
        Tableing fourthTableEntity=null;
        int i=0;
        while(i<tableEntity.size()){
            Tableing temp=tableEntity.get(i);
            if(temp.getPk().getKey_value()==key && temp.getPk().getMinor()==firstTable){
                if(flag[0]) {
                    i++;
                    continue;
                }else{
                    flag[0]=true;
                    firstTableEntity=temp;
                }
            }else if(temp.getPk().getKey_value()==key && temp.getPk().getMinor()==secondTable){
                if(flag[1]) {
                    i++;
                    continue;
                }else{
                    flag[1]=true;
                    secondTableEntity=temp;
                }
            }else if(temp.getPk().getKey_value()==key && temp.getPk().getMinor()==thirdTable){
                if(flag[2]) {
                    i++;
                    continue;
                }else{
                    flag[2]=true;
                    thirdTableEntity=temp;
                }
            }else if(temp.getPk().getKey_value()==key && temp.getPk().getMinor()==fourthTable){
                if(flag[3]) {
                    i++;
                    continue;
                }else{
                    flag[3]=true;
                    fourthTableEntity=temp;
                }
            }
            i++;
        }
        ResultString result1;
        ResultString result2;
        ResultString result3;
        ResultString result4;
        if(firstTableEntity==null)
            firstTableEntity=new Tableing(new EmbeddableId(0, firstTable), 1111, sdf.format(nowDate));
        if(secondTableEntity==null)
            secondTableEntity=new Tableing(new EmbeddableId(0, secondTable), 1111, sdf.format(nowDate));
        if(thirdTableEntity==null)
            thirdTableEntity=new Tableing(new EmbeddableId(0, thirdTable), 1111, sdf.format(nowDate));
        if(fourthTableEntity==null)
            fourthTableEntity=new Tableing(new EmbeddableId(0, fourthTable), 1111, sdf.format(nowDate));
        result1=new ResultString(firstTableEntity);
        result2=new ResultString(secondTableEntity);
        result3=new ResultString(thirdTableEntity);
        result4=new ResultString(fourthTableEntity);

        model.addAttribute("firstEntity", result1);
        model.addAttribute("secondEntity", result2);
        model.addAttribute("thirdEntity", result3);
        model.addAttribute("fourthEntity", result4);

        return "greenRoom";
    }
    @GetMapping("/MentoringRoom")
    public String mentoringRoomIndex(Model model){
        int firstRoom=11;
        int secondRoom=12;
        int thirdRoom=13;
        int fourthRoom=14;

        List<Tableing> roomAll=tr.findAll();
        Tableing LastInput = new Tableing();
        for(Tableing room:roomAll)
            LastInput=room;
        Integer key=LastInput.getPk().getKey_value();

        Date nowDate=new Date();

        Date startTime=new Date();
        Date endTime=new Date();

        startTime.setMinutes(endTime.getMinutes()-3);
        startTime.setSeconds(endTime.getSeconds()-30);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Tableing> roomEntity=tr.findByTimeBetween(sdf.format(startTime), sdf.format(endTime));
        boolean[] flag={false, false, false, false};
        Tableing firstRoomEntity=null;
        Tableing secondRoomEntity=null;
        Tableing thirdRoomEntity=null;
        Tableing fourthRoomEntity=null;
        int i=0;
        while(i<roomEntity.size()){
            Tableing temp=roomEntity.get(i);
            if(temp.getPk().getKey_value()==key && temp.getPk().getMinor()==firstRoom){
                if(flag[0]) {
                    i++;
                    continue;
                }else{
                    flag[0]=true;
                    firstRoomEntity=temp;
                }
            }else if(temp.getPk().getKey_value()==key && temp.getPk().getMinor()==secondRoom){
                if(flag[1]) {
                    i++;
                    continue;
                }else{
                    flag[1]=true;
                    secondRoomEntity=temp;
                }
            }else if(temp.getPk().getKey_value()==key && temp.getPk().getMinor()==thirdRoom){
                if(flag[2]) {
                    i++;
                    continue;
                }else{
                    flag[2]=true;
                    thirdRoomEntity=temp;
                }
            }else if(temp.getPk().getKey_value()==key && temp.getPk().getMinor()==fourthRoom){
                if(flag[3]) {
                    i++;
                    continue;
                }else{
                    flag[3]=true;
                    fourthRoomEntity=temp;
                }
            }
            i++;
        }
        MentoringResult result1;
        MentoringResult result2;
        MentoringResult result3;
        MentoringResult result4;
        if(firstRoomEntity==null)
            firstRoomEntity=new Tableing(new EmbeddableId(0, firstRoom), 1111, sdf.format(nowDate));
        if(secondRoomEntity==null)
            secondRoomEntity=new Tableing(new EmbeddableId(0, secondRoom), 1111, sdf.format(nowDate));
        if(thirdRoomEntity==null)
            thirdRoomEntity=new Tableing(new EmbeddableId(0, thirdRoom), 1111, sdf.format(nowDate));
        if(fourthRoomEntity==null)
            fourthRoomEntity=new Tableing(new EmbeddableId(0, fourthRoom), 1111, sdf.format(nowDate));
        result1=new MentoringResult(firstRoomEntity);
        result2=new MentoringResult(secondRoomEntity);
        result3=new MentoringResult(thirdRoomEntity);
        result4=new MentoringResult(fourthRoomEntity);

        model.addAttribute("firstEntity", result1);
        model.addAttribute("secondEntity", result2);
        model.addAttribute("thirdEntity", result3);
        model.addAttribute("fourthEntity", result4);

        return "mentoringRoom";
    }
    @GetMapping("/project-details")
    public String projectDetailsIndex(){
        return "projectdetails";
    }
    @GetMapping("/who-makes")
    public String whoMakesIndex(){
        return "Whomakes";
    }
    @GetMapping("/MentoringRoom_Demo")
    public String mRoomDemo(){
        return "MentoringDemo";
    }
    @GetMapping("/BookCafe_Demo")
    public String bcDemo(){
        return "bookCafeDemo";
    }
}
