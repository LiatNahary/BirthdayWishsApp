package com.example.BD;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import java.time.LocalDate;

public class Recipient {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("Name")
    private String name;

    @ExcelCellName("DOB")
    private LocalDate birthDay;

    @ExcelCellName("phone #")
    private String number;

    @ExcelCellName("formality")
    private String formality;

    public Recipient() {
    }

    public Recipient(String name, LocalDate birthDay, String number, String formality) {
        this.name = name;
        this.birthDay = birthDay;
        this.number = number;
        this.formality= formality;
    }

    public String getName() {
        return name;
    }
    public LocalDate getBirthDay() {
        return birthDay;
    }
    public String getNumber() {
        return number;
    }
    public String getFormality() {
        return formality;
    }


    @Override
    public String toString() {
        return "Recipient [rowIndex=" + rowIndex + ", name=" + name + ", DOB=" + birthDay + ", phone #=" + number+ ", formality="+ formality+ "]";
    }

}
