# post-sample-hilt-jetpack                      
기존 [post-sample Repository](https://github.com/HunSeongPark/post-sample)에서 새로 학습한 부분 도입                      

## 기존 코드 변경                     
- (Observe) LiveData -> Flow / StateFlow                        
- (DI) Dagger2 -> Hilt                      
- (Asynchronous) RxJava -> Coroutine / Flow
- (Json Converter) Gson -> Moshi                      
- (Navigate) _ -> Jetpack Navigation (Single Activity)                              

## 추가된 기능  
- (Custom Dialog) Shared Preferences 활용한 프로필 이름 설정, 게시글 및 유저 찜 기능                     
- My Tab에서 ViewPager2 사용하여 찜한 게시글 및 유저 확인 기능                              

## 추가로 학습하게 된 것                             
- [Flow block 내에서 여러 개의 suspend function에 대한 비동기 처리](https://hungseong.tistory.com/32)                        
- [Room DB에서 Flow를 사용하여 DB 변경 observing](https://hungseong.tistory.com/33)                        
