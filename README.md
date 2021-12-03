# post-sample-hilt-jetpack                      
기존 [post-sample Repository](https://github.com/HunSeongPark/post-sample)에서 새로 학습한 부분 도입                      

## 기존 코드 변경                     
- (Observe) LiveData -> Flow / StateFlow                        
- (DI) Dagger2 -> Hilt                      
- (Asynchronous) RxJava -> Coroutine / Flow
- (Json Converter) Gson -> Moshi                      
- (Navigate) _ -> Jetpack Navigation (Single Activity)                  

## 추가할 기능  
- Room DB 활용한 프로필 이름 설정, 게시글 및 유저 찜 기능                     
- My Tab에서 ViewPager2 사용하여 찜한 게시글 및 유저 확인 기능                        
