struct GreeterRequest {
  1: i64 requestId,
  2: string sender,
  3: string greeting
}

struct GreeterResponse {
  1: i64 requestId,
  2: bool success
}

service GreetingService {
    GreeterResponse greet(1:GreeterRequest request),
}