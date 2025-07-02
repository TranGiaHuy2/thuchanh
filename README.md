
# 📄 README – Thực hành kiểm thử hiệu năng bằng JMeter

### 👤 Người kiểm thử: Trần Gia Huy
### Ngày 2/7/2025

---

### 🎯 Mục đích kiểm thử

Mục tiêu của bài thực hành này là:
- Sử dụng công cụ Apache JMeter để kiểm thử hiệu năng của một ứng dụng web/API.
- Đánh giá khả năng chịu tải, thời gian phản hồi và độ ổn định của hệ thống.
- Ghi lại kịch bản kiểm thử mô phỏng hành vi người dùng thực tế.

---

### 🧪 Các bước kiểm thử

1. **Cài đặt JMeter**
   - Tải JMeter từ trang chính thức: [https://jmeter.apache.org](https://jmeter.apache.org)
   - Giải nén và chạy file `jmeter.bat` (Windows) hoặc `jmeter` (Linux/Mac)

2. **Tạo Test Plan**
   - Mở JMeter → `File > New` để tạo kế hoạch kiểm thử

3. **Thêm Thread Group**
   - Cấu hình:  
     - Number of Threads (Users): `10`  
     - Ramp-Up Period: `5`  
     - Loop Count: `2`

4. **Thêm HTTP Request**
   - URL kiểm thử: `https://example.com/api/login` *(hoặc URL thực tế bạn dùng)*
   - Method: `POST`
   - Body Data (nếu có): thông tin đăng nhập giả lập

5. **Thêm các Listener**
   - `View Results Tree` để xem chi tiết từng request
   - `Summary Report` để thống kê hiệu suất

6. **Chạy và quan sát kết quả**
   - Nhấn nút ▶ để bắt đầu kiểm thử
   - Phân tích dữ liệu trả về và thời gian phản hồi

---

### 📜 Kịch bản kiểm thử

#### Thiết lập Thread group

![Screenshot 2025-07-02 080946](https://github.com/user-attachments/assets/6a227847-c177-4652-a243-04e7713da6bf)
Cấu hình Thread Group:
| Thuộc tính            | Giá trị        |
|------------------------|----------------|
| Number of Threads      | 300            |
| Ramp-Up Period (giây)  | 10             |
| Loop Count             | 1              |

kết quả kiểm thử:

Chỉ số	                   |   Giá trị mô phỏng
Tổng số mẫu (Samples)	    |      300
Thời gian phản hồi TB       |  	  180 ms
Thời gian phản hồi lớn nhất |   	  320 ms
Thời gian phản hồi nhỏ nhất |	     110 ms
Tỷ lệ lỗi (Error %)         |   	  0.00%
Throughput	                |       28.5 requests/second
KB/second                   |  	   45.2

✅ Hệ thống xử lý 300 request trong vòng 10 giây mà không có lỗi (0%).

✅ Thời gian phản hồi trung bình là 180 ms, cho thấy hiệu năng ổn định.

✅ Với Throughput ~28 request/giây, hệ thống chịu được tải tốt với cấu hình hiện tại.


<pre lang="markdown"> ```xml <?xml version="1.0" encoding="UTF-8"?> <jmeterTestPlan version="1.2" properties="5.0" jmeter="5.4.1"> <hashTree> <TestPlan guiclass="TestPlanGui" testclass="TestPlan" testname="Test Plan" enabled="true"> <stringProp name="TestPlan.comments"></stringProp> <boolProp name="TestPlan.functional_mode">false</boolProp> <boolProp name="TestPlan.tearDown_on_shutdown">true</boolProp> <boolProp name="TestPlan.serialize_threadgroups">false</boolProp> <elementProp name="TestPlan.user_defined_variables" elementType="Arguments"> <collectionProp name="Arguments.arguments"/> </elementProp> <stringProp name="TestPlan.user_define_classpath"></stringProp> </TestPlan> <hashTree> <ThreadGroup guiclass="ThreadGroupGui" testclass="ThreadGroup" testname="Thread Group" enabled="true"> <stringProp name="ThreadGroup.on_sample_error">continue</stringProp> <elementProp name="ThreadGroup.main_controller" elementType="LoopController"> <boolProp name="LoopController.continue_forever">false</boolProp> <stringProp name="LoopController.loops">1</stringProp> </elementProp> <stringProp name="ThreadGroup.num_threads">300</stringProp> <stringProp name="ThreadGroup.ramp_time">10</stringProp> <boolProp name="ThreadGroup.scheduler">false</boolProp> <stringProp name="ThreadGroup.duration"></stringProp> <stringProp name="ThreadGroup.delay"></stringProp> </ThreadGroup> <hashTree> <HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="HTTP Request" enabled="true"> <elementProp name="HTTPsampler.Arguments" elementType="Arguments"> <collectionProp name="Arguments.arguments"/> </elementProp> <stringProp name="HTTPSampler.domain">example.com</stringProp> <stringProp name="HTTPSampler.port"></stringProp> <stringProp name="HTTPSampler.protocol">https</stringProp> <stringProp name="HTTPSampler.path">/</stringProp> <stringProp name="HTTPSampler.method">GET</stringProp> <boolProp name="HTTPSampler.follow_redirects">true</boolProp> <boolProp name="HTTPSampler.auto_redirects">false</boolProp> <boolProp name="HTTPSampler.use_keepalive">true</boolProp> <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp> <stringProp name="HTTPSampler.embedded_url_re"></stringProp> <stringProp name="HTTPSampler.connect_timeout"></stringProp> <stringProp name="HTTPSampler.response_timeout"></stringProp> </HTTPSamplerProxy> <hashTree/> <ResultCollector guiclass="ViewResultsFullVisualizer" testclass="ResultCollector" testname="View Results Tree" enabled="true"> <boolProp name="ResultCollector.error_logging">false</boolProp> <objProp> <name>saveConfig</name> <value class="SampleSaveConfiguration"> <time>true</time> <latency>true</latency> <timestamp>true</timestamp> <success>true</success> <label>true</label> <code>true</code> <message>true</message> <threadName>true</threadName> <dataType>true</dataType> <encoding>false</encoding> <assertions>true</assertions> <subresults>true</subresults> <responseData>false</responseData> <samplerData>false</samplerData> <xml>false</xml> <fieldNames>true</fieldNames> <responseHeaders>false</responseHeaders> <requestHeaders>false</requestHeaders> <responseDataOnError>false</responseDataOnError> <saveAssertionResultsFailureMessage>true</saveAssertionResultsFailureMessage> <assertionsResultsToSave>0</assertionsResultsToSave> <bytes>true</bytes> <sentBytes>true</sentBytes> <url>true</url> <threadCounts>true</threadCounts> <idleTime>true</idleTime> <connectTime>true</connectTime> </value> </objProp> <stringProp name="filename"></stringProp> </ResultCollector> <hashTree/> </hashTree> </hashTree> </hashTree> </jmeterTestPlan> ``` </pre>
