import java.util.Random;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        int[] com = random3Num();

        System.out.print("랜덤확인");
        for(int i=0; i<3; i++){
            System.out.print(com[i]);
        }
        System.out.println();

        while(true) {
            int[] user = userInput();

//            System.out.print("입력확인");
//            for(int i=0; i<3; i++){
//                System.out.print(user[i]);
//            }
//            System.out.println();

            if (isAnswer(com, user)) {
                break;
            }
            else{
                printBallStrike(com, user);
            }
        }
    }

    /**
     * 중복이 없는 3숫자를 배열로 반환한다.
     * @return 랜던 숫자 배열
     */
    public static int[] random3Num(){
        int[] ranNum3 = new int[3];
        Random random = new Random();

        for (int i=0; i<3; i++){
            ranNum3[i] = random.nextInt(9) + 1;
            for(int j=0; j<i; j++){
                if(ranNum3[i] == ranNum3[j]){
                    i--;
                }
            }
        }
        return ranNum3;
    }

    /**
     * 사용자의 입력을 받아 유효성 검사후 반환한다.
     * @return 사용자가 입력한 숫자 배열
     */
    public static int[] userInput(){
        Scanner input = new Scanner(System.in);
        int[] user3Num = new int[3];
        System.out.print("숫자를 입력해 주세요 : ");
        String inputNum = input.next();
        isError(inputNum);
        for(int i=0; i<3; i++){
            user3Num[i] = Character.getNumericValue(inputNum.charAt(i));
        }
        return user3Num;
    }

    /**
     * 사용자의 입력의 유효성을 검사한다.
     * 잘못된 입력
     * 1. 길이가 3이상
     * 2. 하나라도 숫자가 아닌 경우
     * 3. 숫자가 겹치는 경우
     * @param ss 사용자 입력
     */
    public static void isError(String ss){
        if(ss.length() != 3) {
            throw new IllegalStateException("잘못된 입력입니다. (숫자는 세자리 입니다.)");
        }
        for(int i=0; i<2; i++){
            for(int j=i+1; j<3; j++){
                if(ss.charAt(i) == ss.charAt(j)){
                    throw new IllegalStateException("잘못된 입력입니다. (숫자는 중복되지 않습니다.)");
                }
            }
        }
        if(ss.chars().allMatch(Character::isDigit)){
            return;
        }
        else{
            throw new IllegalStateException("잘못된 입력입니다. (입력은 숫자입니다.)");
        }
    }

    /**
     * 정답이 맞는지 확인한다.
     * @param com 랜덤 배열
     * @param user 사용자 입력 배열
     * @return 정답 여부
     */
    public static boolean isAnswer(int[] com, int[] user){
        for(int i=0; i<3; i++){
            if(com[i] != user[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 스트라이크 개수를 구한다.
     * @param com 랜덤 숫자 배열
     * @param user 사용자 입력 배열
     * @return 스트라이크 개수
     */
    public static int strike(int[] com, int[] user){
        int strickNum = 0;

        for(int i=0; i<3; i++){
            if(com[i] == user[i]){
                strickNum++;
            }
        }

        return strickNum;
    }

    /**
     * 볼 개수를 구한다.
     * @param com 랜덤 숫자 배열
     * @param user 자용자 입력 배열
     * @return 불 개수
     */
    public static int ball(int[] com , int[] user){
        int ball = 0;
        for(int i = 0; i<3; i++){
            for(int j=0; j<3; j++){
                if(com[i] == user[j]){
                    ball++;
                }
            }
        }
        ball = ball - strike(com, user);
        return ball;
    }

    /**
     * 스트라이크, 볼 갯수를 출력한다.
     * @param com 랜덤 숫자 배열
     * @param user 사용자 입력 배열
     */
    public  static void printBallStrike(int[] com, int[] user){
        int strike = strike(com, user);
        int ball = ball(com, user);
        if(strike == 0 && ball == 0){
            System.out.println("낫싱");
        }
        else if(strike == 0){
            System.out.println(ball + "볼");
        }
        else if(ball == 0){
            System.out.println(strike + "스트라이크");
        }
        else{
            System.out.println(ball + "볼 " + strike + "스트라이크");
        }
    }
}