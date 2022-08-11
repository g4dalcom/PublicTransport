public class Bus extends PublicTransport {
  // 필드
  int maxPassenger = 30;
  String status = "운행";
  int basicfare = 1000;
  static int count = 0;

  // 버스번호와 상태를 받는 생성자
  public Bus() {
      ++count;
      this.number = count;
      this.status = status;
    System.out.println("버스번호 : "+this.number+ ", 상태 : "+this.status);
  }

  // 주유
  public void fuel(int fuel) {
    this.fuelVolume += fuel;
    // 주유량이 10 미만일 경우 경고메시지와 함께 상태가 변경됨
    if(fuelVolume < 10) {
    System.out.println("경고! 주유 필요");
    this.status = "차고지행";
    }
    System.out.println("남은주유량 : "+this.fuelVolume);
    System.out.println("상태 : "+this.status);
    }

  // 승객 태우기
  public void take(int passenger) {
  if(this.status == "운행") {
    this.passenger = passenger;
    this.maxPassenger -= passenger;
    this.fare = passenger * basicfare;
    // 잔여승객수보다 승객수가 많으면 승객을 태우지 않음
    if(maxPassenger < 0) {
      System.out.println("경고! 최대 승객 수 초과");
      this.maxPassenger += passenger;
      this.fare -= passenger * basicfare;
      this.passenger -= passenger;
    }
    System.out.println("탑승자수 : "+this.passenger+", 잔여승객수 : "+this.maxPassenger+", 요금확인 : "+this.fare);
    }
  }


  public static void main(String[] args) {
    System.out.println("== bus 2대 생성 ==");
    Bus bus1 = new Bus();
    Bus bus2 = new Bus();

    bus1.runStart();

    System.out.println("== bus1에 승객 2명 탑승 ==");
    bus1.take(2);

    System.out.println("== 주유량 변동(-50) ==");
    bus1.fuel(-50);

    System.out.println("== 버스1 상태 변경 ==");
    bus1.statusChange("차고지행");

    System.out.println("== 주유량 변동(+10) ==");
    bus1.fuel(10);

    System.out.println("== bus1 상태 변경 ==");
    bus1.statusChange("운행중");

    System.out.println("== 승객 +45명 ==");
    bus1.take(45);

    System.out.println("== 승객 +5명 ==");
    bus1.take(5);

    System.out.println("== 주유량 변동(-55) ==");
    bus1.fuel(-55);
  }
}
