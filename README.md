# ConfigBuildGradle
## Gralde 
- Gralde là bộ công cụ build ứng dụng hỗ trợ nhiều ngôn ngữ. Nếu bạn đang muốn build, testing hay publish ứng dung trên bất kì nền tảng nà, Gradle cung cấp một mô hình linh hoạt có thể hỗ trợ toàn bộ vòng đời từ complie, đóng gói code cho đến pulic ứng dụng. Gradle hỗ trợ tự động hóa xây dựng nhiều ngôn ngữ và nền tảng bao gồm Java, Scala, Android, C/C++ và Groovy, nó được tích hợp sẵn trên các ide phổ biến như Eclipse, InteliJ, AndroidStudio, ...
## Gralde in Android Stuido 
- Android Studio sử dụng Gralde làm công cụ build ứng dụng nhờ nó mà ta có thể quản lí các version built một cách dễ dàng. Với mỗi cấu hình cài đặt nó định nghĩa source code và resource, trong khi sử dụng lại các phần chung cho tất cả các phiên bản ứng dụng.
- Gradle và Android plugin chạy độc lập trong Android Studio. Điều này có nghĩa là có thể built Android app trong Android Studio bằng command line bằng Gralde Wrapper.
- Gradle sử dụng Groovy làm DSL nó miêu tả và điều khiển quá trình built ứng dụng.
- Groovy được xuất hiện từ năm 2013 khi Google IO công bố Android Studio sử dụng Gradle build với groovy script. Groovy là một ngôn ngữ lập trình hướng đối tượng trên nền Java. Nó là một ngôn ngữ lập trình động với các tính năng tương tự như Python, Ruby, Perl, và Smalltalk. Hơn nữa, nó cũng có thể được sử dụng như là một ngôn ngữ kịch bản chạy trên nền máy ảo Java.
## Task
- Nói không sai Task chính là trái tim của Gradle. Một Task trong Gradle đơn giản thì là một đơn vị công việc mà Gradle có thể hiểu và chạy được nó và phần core của Task chính là Action.

- Giả sử chúng ta có thể mô tả một task compile một vài Java sources hoặc copy một số file từ thư mục này sang thư mục khác, hay đơn giản chỉ là in ra dòng chữ "Hello Gradle". Một task có thể làm những việc độc lập như in ra dòng chữ "XXX" hoặc có thể chạy tạo các dependencies với những Task khác. Gradle sẽ đảm bảo tất cả dependencies sẽ được chạy.

- Một task còn có thể định nghĩa inputs và outputs, như một java method, ngoài ra nó còn có thể config để đọc và ghi file nữa.
- Chúng ta có thể tự tạo ra một task sử dụng Grovvy hay Kotlin để chạy chúng. 
## Gradle Wrapper
- Tìm hiểu về Gradle Wrapper là cách nhanh nhất tiếp cận với Gradle build. Nó là một batch script trên Windows, và shell scprit trên các thiết bị khác. Ta có thể chạy các Task build gradle với terminal của android bằng ./gradlew task-name gradlew(gradlew(rapper).
- Ta có thể xem list  built task có sẵn trong project bằng:  gradlew tasks
- Gradle Wrapper gồm số file trong project của bạn 
+ gradlew.bat : Bat script in window mà chúng ta có thể chạy các task
+ gradle/wrapper/gradle-wrapper.jar: đây là nơi chứa code của gradle wrapper
+ gradle/wrapper/gradle-wrapper.properties : đây là nơi chứa thuộc tính cấu hình gradle wrapper
## Config Build Gradle
### Config Build Type
- Khi build app của bạn ta có thể tùy chọn kiểu build mà ta đã định nghĩa trong build type. Build type xác định những thuộc tính mà Gradle sử dụng khi build app của bạn, tùy theo quá trình phát triển mà ta build app khác nhau. Ví dụ khi build app debug sẽ bật trình debug lên, còn khí build app bản release ta cần bảo vệ source code và không cần bật chế độ debug, ta có thể tạo thêm một số thuộc tính cho những kiểu build này

        buildTypes {
              release {
                  minifyEnabled true
                  proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
              }

              debug {
                  applicationIdSuffix ".debug"
                  debuggable true
              }
              
              staging {
                  initWith debug
                  manifestPlaceholders = [hostName:"internal.example.com"]
                  applicationIdSuffix ".debugStaging"
              }
           }
       }
       
### Config Product flavor
Product flavor đại diện cho các phiên bản khác nhau mà chúng ta release cho user như phiên bản free hay phiên bản trả phí hay thông thường khi ta phát triển ứng dụng trong dự án có vài môi trường như DEV, Staging, Production. Ta có thể config chúng với Product flavor.








 
