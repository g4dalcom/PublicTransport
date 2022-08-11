public class PublicTransport {
    int fuelVolume = 100;
    int number;
    int speed = 0;
    int speedChange; 
    int fare;
    int acc_fare;
    int passenger;
    String status;
    String destination;

    // 운행시작
    public void runStart() {
        if(fuelVolume >= 10) {
          this.status = "운행";
          this.speed = 60;
          System.out.println("남은주유량 : "+this.fuelVolume+", 상태 : "+this.status+", 속도 : "+this.speed+"km, 운행을 시작합니다.");
        } else {
          System.out.println("주유량을 확인해주세요.");
        }
    }

    // 속도 변경
    public void speedChange(int speed) {
        if(fuelVolume >= 10) {
          this.speed += speed;
          System.out.println("속도를 "+speed+"만큼 변경합니다. 현재속도 : "+this.speed+"km");
        } else {
          System.out.println("주유량을 확인해주세요.");
        }
    }

    // 상태변경
    public void statusChange(String status) {
      this.status = status;
      System.out.println("상태 : "+this.status);
    }
}
