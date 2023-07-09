package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

    // List<Grade> studentGrades=Arrays.asList(
    //     new Grade("Harry", "potions", "C-"),
    //     new Grade("hermoine", "Arithmancy", "A+"),
    //     new Grade("neville", "Charms", "A-")
    // );

        List<Grade> studentGrades=new ArrayList<>();

    @GetMapping("/grades")
    public String GetGrades(Model model){
        // studentGrades.add(new Grade("Harry", "potions", "C-"));
        //    studentGrades.add(new Grade("hermoine", "Arithmancy", "A+"));
        //       studentGrades.add(new Grade("neville", "Charms", "A-"));

  //we are removing it because we have created arraylist

        //  Grade grade=new Grade("Harry", "potions", "C-");
        // model.addAttribute("grade", grade);

            model.addAttribute("grades", studentGrades);
        return "grades";
    }

 

    @GetMapping("/shows")
    public String GetShows(Model kaps){
        shows a=new shows("braking bad", "?ozymandias", 10);
        kaps.addAttribute("shows",a);
        return "shows";
    }

     //function for updating
       public Integer getGradeindex(String id){
        for(int i=0;i<studentGrades.size();i++){
            if(studentGrades.get(i).getId().equals(id)){
                return i;
            }
     
        }
              return constants.NOT_Found;
    }
    @GetMapping("/")
    public String gradeform(Model model, @RequestParam(required=false) String id){
 
        //shorter method to check update fields are correct
    //     if(getGradeindex(name)==-1000){
    //         grade=new Grade();
    //     }else{
    //         grade=studentGrades.get(getGradeindex(name));
    //     }
    //    model.addAttribute("grade",new Grade() );
        int index=getGradeindex(id);
    model.addAttribute("grade", index==constants.NOT_Found?new Grade():studentGrades.get(index));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitGrade(Grade grade){
        int index=getGradeindex(grade.getId());
        if(index==constants.NOT_Found){
            studentGrades.add(grade);
        }else{
            studentGrades.set(index,grade);
        }

  
   return "redirect:/grades";
    }

}
