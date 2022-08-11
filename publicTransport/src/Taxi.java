public class Taxi extends PublicTransport {
  //필드
  int maxPassenger = 4;
  int baserate = 3000;
  int farePerDistance = 1000;
  int distance = 1;
  int basic_distance = 10;
  static int count = 0;
  
  // 택시번호와 상태와 주유량을 받는 생성자
  public Taxi(int number) {
    count++;
    this.number = count;
    this.status = "일반";
    this.fuelVolume = 100;
    System.out.println("택시번호 : "+this.number+ ", 상태 : "+this.status);
  }

  // 운행시작
  public void runStart() {
    if(fuelVolume >= 10) {
      this.status = "일반";
      this.speed = 60;
      System.out.println("남은주유량 : "+this.fuelVolume+", 상태 : "+this.status+", 속도 : "+this.speed+"km, 운행을 시작합니다.");
    } else {
      System.out.println("주유량을 확인해주세요.");
    }
  }

  // 승객 태우기, 목적지까지의 거리가 10km(basic_distance)를 초과하면 초과한 km수 만큼 거리당 1000원의 요금을 추가 요금으로 받음
  public void take(int passenger, String destination, int distance) {
    if(this.status == "일반") {
      this.passenger = passenger;
      this.maxPassenger -= passenger;
      if(distance > basic_distance) {
        this.fare = baserate + (farePerDistance * (distance - basic_distance));
      } else {
        this.fare = baserate;
      }
      this.status = "운행중";
      // 잔여승객수보다 승객수가 많으면 승객을 태우지 않음
      if(maxPassenger < 0) {
          System.out.println("경고! 최대 승객 수 초과");
          this.maxPassenger += passenger;
          this.fare -= baserate + (farePerDistance * distance);
          this.passenger -= passenger;
          this.status = "일반";
      }
      System.out.println("탑승승객수 : "+this.passenger+", 잔여승객수 : "+this.maxPassenger+", 기본요금확인 : "+baserate+", 목적지 : "+destination+", 목적지까지 거리 : "+distance+"km, 지불할요금 : "+this.fare+", 상태 : "+this.status);
    } else {
      System.out.println("탑승불가!");
    }
    }

  // 주유
  public void fuel(int fuel) {
    this.fuelVolume += fuel;
    // 주유량이 10 미만일 경우 경고메시지와 함께 상태가 변경됨
    if(fuelVolume < 10) {
    System.out.println("경고! 주유 필요");
    this.status = "운행불가";
    }
    System.out.println("남은주유량 : "+this.fuelVolume);
    System.out.println("상태 : "+this.status);
    }

  // 요금 결제
  public void payment(int fare) {
    this.acc_fare += fare;
    this.maxPassenger += this.passenger;
    this.passenger -= this.passenger;
    this.status = "일반";
    System.out.println(this.fare+"원을 정산하였습니다.");
    System.out.println("누적 요금 : "+acc_fare);
    System.out.println("탑승승객수 : "+this.passenger+", 잔여승객수 : "+this.maxPassenger+", 상태 : "+this.status+", 남은주유량 : "+this.fuelVolume);
  }    


  public static void main(String[] args) {
    System.out.println("== Taxi 2대 생성 ==");
    Taxi taxi1 = new Taxi(1);
    Taxi taxi2 = new Taxi(2);

    taxi1.runStart();

    System.out.println("== 승객 2명 탑승 ==");
    taxi1.take(2, "서울역", 2);

    System.out.println("== 주유량 변동(-80) ==");
    taxi1.fuel(-80);

    System.out.println("== 요금 결제 ==");
    taxi1.payment(taxi1.fare);

    System.out.println("== 승객 5명 탑승 시도 ==");
    taxi1.take(5, "미정", 0);

    System.out.println("== 승객 3명 탑승 ==");
    taxi1.take(3, "구로디지털단지역", 12);

    System.out.println("== 요금 결제 ==");
    taxi1.payment(taxi1.fare);

    System.out.println("== 주유량 변동(-20)");
    taxi1.fuel(-20);
  }
}
