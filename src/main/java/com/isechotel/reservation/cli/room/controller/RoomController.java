package com.isechotel.reservation.cli.room.controller;

import com.isechotel.reservation.cli.main.AppController;
import com.isechotel.reservation.cli.room.domain.Room;
import com.isechotel.reservation.cli.room.domain.SearchCondition;
import com.isechotel.reservation.cli.room.repository.MemoryRoomRepository;
import com.isechotel.reservation.cli.room.repository.RoomRepository;
import com.isechotel.reservation.cli.ui.AppUI;

import java.util.List;

public class RoomController implements AppController {

    // 의존 관계 설정
    private final RoomRepository roomRepository = new MemoryRoomRepository();

    @Override
    public void start() {

        while(true){
            AppUI.roomManagementScreen();
            System.out.println("원하시는 메뉴 번호를 선택해주세요.");
            int selection = AppUI.inputInteger("---▶  ");

            switch (selection){
                case 1:
                    insertRoomData();
                    break;
//                case 2:
//                    removeRoomData();
//                    break;
                case 2:
                    searchRoomData();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("[ 메뉴를 잘못 선택하셨습니다! ]");
            }
            System.out.println("---▶  [ENTER]를 누르면 계속 진행됩니다!");
            AppUI.inputString("");

        }//end while
    }// end start


    // 객실 추가 기능
    private void insertRoomData() {
        System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━✦❘༻ 객실 추가 ༺❘✦━━━━━━━━━━━━━━━━┓");
        String roomName = AppUI.inputString("--—▶  객실명 : ");
        int roomCharge = AppUI.inputInteger("--—▶  객실금액 : ");
        Room newRoom = new Room(roomName, roomCharge);

        roomRepository.addRoom(newRoom);
        System.out.printf("\n┗━━━━━✦❘༻ [%s] 객실 정보가 정상 등록되었습니다! ༺❘✦━━━━━━━┛\n", newRoom.getRoomName());
    }

/*
    // 객실 삭제 기능
    private void removeRoomData() {

    }
*/

    // 객실 정보 검색 기능
    private void searchRoomData() {
        AppUI.searchRoomConditionScreen();
        int selection = AppUI.inputInteger("---▶  ");

        SearchCondition condition = SearchCondition.ALL;

        switch (selection){
            case 1:
                System.out.println("\n━━━━⊱* [객실명] 으로 검색 *⊰━━━━━");
                condition = SearchCondition.ROOM_NAME;
                break;
            case 2:
                System.out.println("\n━━━━⊱* [객실번호] 로 검색 *⊰━━━━━");
                condition = SearchCondition.ROOM_NUMBER;
                break;
            case 3:
                condition = SearchCondition.ALL;
                break;
            default:
                System.out.println("[ 메뉴를 잘못 선택하셨습니다! ]");
        }

        String keyword = "";
        if(condition != SearchCondition.ALL){
            keyword = AppUI.inputString("---▶  검색어 : ");
        }
        List<Room> roomList = roomRepository.searchRoom(keyword, condition);
        int cnt = roomList.size();
        if(cnt > 0){
            System.out.printf("\n━━━━━━━━━━━━━━━━━━━━━━⊱* 검색 결과  *⊰━━━━━━━━━━━━━━━━━━━━━━━━━\n[ 총 %d건 조회되었습니다! ]\n", cnt);
            for (Room room : roomList) {
                System.out.println(room);
            }
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━⊱*");

        }else{
            System.out.println("━━━━⊱* [ 검색 결과가 없습니다! ] *⊰━━━━━");
        }
    }

}