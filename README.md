# 자바 채팅프로그램

-------------------

### 자바 소켓프로그래밍

![Socket](https://github.com/DustinYook/Java_ChattingProgram/blob/master/image/Socket.PNG)

1. Server 측에서는 ServerSocket 을 생성하고 accept() 메서드를 호출함으로써 Client 의 접속을 대기.
2. Client 측에서는 Server 에 접속을 함으로써 Server 와의 통신을 위한 Socket 을 생성.
3. 마찬가지로 Server 측에서 Client 접속이 이루어지면 해당 Client 와 통신 할 수 있는 Socket 을 반환받음.
4. Clinet 와 Server 는 생성된 소켓을 통하여 각각 상대에게 데이터를 내보내기 위한 출력 스트림과 데이터를 읽어 들이기 위한 입력 스트림을 생성.
5. 생성된 스트림을 통하여 Server / Client 서로간에 데이터를 송수신.
6. 통신이 끝나면 Client 와 Server 측에서 각각 socket.close() 를 해줌으로써 통신을 종료.

[출처] : <https://blog.naver.com/bsw2428/221324339019>

### Client GUI
![Client](https://github.com/DustinYook/Java_ChattingProgram/blob/master/image/ClientGUI.PNG)

### Server
![Server](https://github.com/DustinYook/Java_ChattingProgram/blob/master/image/Server.PNG)
