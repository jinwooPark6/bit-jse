package day08;
// 학생 뷰어를

// 필드안에
// 스캐너와 배열을 활용해서
// BordViewer 처럼 만들어보세요

import java.util.Scanner;

import day06.Student;
import util.ArrayUtil;
import util.ScannerUtil;

public class StudentViewer {
    private Scanner scanner;
    private Student[] studentArray;

    // 생성자를 사용하여 필드를 초기화
    public StudentViewer() {
        scanner = new Scanner(System.in);
        studentArray = new Student[0];
    }

    // 1. showMenu출력 메소드
    public void showMenu() {
        while (true) {
            System.out.println("비트 성적관리 프로그램");
            String message = "1. 입력 2. 목록 3. 종료 \n>";
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                addStudent();
            } else if (userChoice == 2) {
                selectAll();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }
    //2. addStudent()
    private void addStudent() {
        Student s = new Student();

        System.out.print("번호: ");
        s.setId(scanner.nextInt());

        System.out.print("이름: ");
        s.setName(ScannerUtil.nextLine(scanner));

        s.setKorean(ScannerUtil.nextInt(scanner, "국어: ", 0, 100));
        s.setEnglish(ScannerUtil.nextInt(scanner, "영어: ", 0, 100));
        s.setMath(ScannerUtil.nextInt(scanner, "수학: ", 0, 100));

        studentArray = ArrayUtil.add(studentArray, s);

    }
    //selectAll()

    private void selectAll() {
            printList();
            String message = "상세보기할 학생의 번호를 입력해주세요(0은 뒤로가기) \n >";
            int userChoice = ScannerUtil.nextInt(scanner, message, 0, studentArray.length) - 1;
            if (userChoice != -1) {
                selectOne(userChoice);
            }
        
    }
    
    private void printList() {
        if(studentArray.length > 0) {
            for(int i = 0; i < studentArray.length; i++) {
                System.out.printf("%d. %s\n", i + 1, studentArray[i].getName());
            }
        }
    }

    private void selectOne(int index) {
        printOne(index);
        String message = "1. 수정 2. 삭제 3. 뒤로가기 \n>";
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
        if (userChoice == 1) {
            updateStudent(index);
        } else if (userChoice == 2) {
            deleteStudent(index);
            selectAll();
        } else if (userChoice == 3) {
            selectAll();
        }

    }
    private void printOne(int index) {
        Student s = studentArray[index];
        System.out.printf("번호: %d번 이름: %s\n", s.getId(), s.getName());
        System.out.printf("국어 점수: %03d점 영어 점수 : %03d점 수학 점수 : %03d점\n", 
                s.getKorean(), s.getEnglish(), s.getMath());

        System.out.printf("총점: %03d점 평균: %.2f점\n", 
                s.calculateSum(), s.calculateAverage());              
    }

    private void updateStudent(int index) {
        

        studentArray[index].setKorean(ScannerUtil.nextInt(scanner, "국어: ", 0, 100));
        studentArray[index].setEnglish(ScannerUtil.nextInt(scanner, "영어: ", 0, 100));
        studentArray[index].setMath(ScannerUtil.nextInt(scanner, "수학: ", 0, 100));

    }

    private void deleteStudent(int index) {
        System.out.print("정말로 삭제하시겠습니까? y/n");
        String agree = ScannerUtil.nextLine(scanner);
        if (agree.equals("y")) {
            studentArray = ArrayUtil.removeByIndex(studentArray, index);
        }
    }

}
