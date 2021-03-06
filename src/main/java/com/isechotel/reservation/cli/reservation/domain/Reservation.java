package com.isechotel.reservation.cli.reservation.domain;

import com.isechotel.reservation.cli.room.domain.Room;
import com.isechotel.reservation.cli.user.domain.User;

import java.time.LocalDate;

public class Reservation {

    private static int sequence;

    private int resNumber; // 예약 번호
    private User user; // 고객 정보
    private Room room; // 객실 정보
    //    private LocalDate checkInDate; // 체크인 날짜
//    private LocalDate checkOutDate; // 체크아웃 날짜
    private Status status; // 예약상태
    private int rentNum;


    // 생성자
    public Reservation(User user, Room room, int rentNum) {
        this.user = user;
        this.room = room;
        this.resNumber = ++sequence;
        this.status = Status.IN;
        this.rentNum = rentNum;
    }

    // getter & setter
    public int getRentNum() {
        return rentNum;
    }

    public void setRentNum(int rentNum) {
        this.rentNum = rentNum;
    }

    public int getResNumber() {
        return resNumber;
    }

    public void setResNumber(int resNumber) {
        this.resNumber = resNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("예약번호 : %d | 고객명 : %s | 연락처 : %s | 예약한 방 : %s\n",
                resNumber, user.getUserName(), user.getPhone(), room.getRoomName());
    }
}
