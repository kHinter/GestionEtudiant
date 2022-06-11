package models;

import java.time.Year;
import java.sql.Date;

public class Student
{
    private String num;
    private String name;
    private String nickname;
    private int age;
    private Date birthdate;
    private String description;
    private String photoUrl;
    private String password;
    private boolean hasRepeated;
    private boolean hasResign;
    private int registrationYear;
    private Group TDGroup;
    private Group TPGroup;
    private Promotion promotion;

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion)
    {
        this.promotion = promotion;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desccription) {
        this.description = desccription;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHasRepeated() {
        return hasRepeated;
    }

    public void setHasRepeated(boolean hasRepeated) {
        this.hasRepeated = hasRepeated;
    }

    public boolean isHasResign() {
        return hasResign;
    }

    public void setHasResign(boolean hasResign) {
        this.hasResign = hasResign;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public Group getTDGroup() {
        return TDGroup;
    }

    public void setTDGroup(Group TDGroup) {
        this.TDGroup = TDGroup;
    }

    public Group getTPGroup() {
        return TPGroup;
    }

    public void setTPGroup(Group TPGroup) {
        this.TPGroup = TPGroup;
    }
}
