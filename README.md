# 📄 README – Thực hành kiểm thử hiệu năng bằng JMeter

### 👤 Người kiểm thử: Trần Gia Huy  
### 📅 Ngày 2/7/2025

---

## 🎯 Mục đích kiểm thử

Mục tiêu của bài thực hành này là:

- Sử dụng công cụ Apache JMeter để kiểm thử hiệu năng của một ứng dụng web/API.
- Đánh giá khả năng chịu tải, thời gian phản hồi và độ ổn định của hệ thống.
- Ghi lại kịch bản kiểm thử mô phỏng hành vi người dùng thực tế.

---

## 🧪 Các bước kiểm thử

1. **Cài đặt JMeter**
   - Tải từ trang chính thức: [https://jmeter.apache.org](https://jmeter.apache.org)
   - Giải nén và chạy `jmeter.bat` (Windows) hoặc `jmeter` (Linux/Mac)

2. **Tạo Test Plan**
   - Mở JMeter → `File > New`

3. **Thêm Thread Group**
   - Cấu hình:  
     - Number of Threads (Users): `10`  
     - Ramp-Up Period: `5`  
     - Loop Count: `2`

4. **Thêm HTTP Request**
   - URL kiểm thử: `https://example.com/api/login` *(hoặc endpoint thực tế)*
   - Method: `POST`

5. **Thêm Listener**
   - `View Results Tree`
   - `Summary Report`

6. **Chạy và quan sát kết quả**
   - Bấm nút ▶ để bắt đầu

---

## 📜 Kịch bản kiểm thử
![Screenshot 2025-07-02 080946](https://github.com/user-attachments/assets/e83a9026-d5be-4c2b-bab5-d228087a3330)
### Thiết lập Thread Group

| Thuộc tính            | Giá trị        |
|------------------------|----------------|
| Number of Threads      | 300            |
| Ramp-Up Period (giây)  | 10             |
| Loop Count             | 1              |

---

### 📊 Kết quả kiểm thử (mô phỏng)

| Chỉ số                     | Giá trị         |
|----------------------------|-----------------|
| Tổng số mẫu (Samples)      | 300             |
| Thời gian phản hồi TB      | 180 ms          |
| Thời gian phản hồi lớn nhất| 320 ms          |
| Thời gian phản hồi nhỏ nhất| 110 ms          |
| Tỷ lệ lỗi (Error %)        | 0.00%           |
| Throughput                 | 28.5 req/sec    |
| KB/second                  | 45.2            |

✅ Hệ thống xử lý 300 request trong 10 giây mà không có lỗi (0%).

✅ Thời gian phản hồi trung bình 180 ms, hiệu năng ổn định.

✅ Với throughput ~28 req/s, hệ thống chịu tải tốt với cấu hình trên.

---

## 🧾 Một phần cấu hình `.jmx`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<jmeterTestPlan version="1.2" properties="5.0" jmeter="5.4.1">
  <hashTree>
    <TestPlan guiclass="TestPlanGui" testclass="TestPlan" testname="Test Plan" enabled="true">
      <boolProp name="TestPlan.functional_mode">false</boolProp>
      <boolProp name="TestPlan.tearDown_on_shutdown">true</boolProp>
      <boolProp name="TestPlan.serialize_threadgroups">false</boolProp>
    </TestPlan>
    <hashTree>
      <ThreadGroup guiclass="ThreadGroupGui" testclass="ThreadGroup" testname="Thread Group" enabled="true">
        <stringProp name="ThreadGroup.num_threads">300</stringProp>
        <stringProp name="ThreadGroup.ramp_time">10</stringProp>
        <elementProp name="ThreadGroup.main_controller" elementType="LoopController">
          <stringProp name="LoopController.loops">1</stringProp>
        </elementProp>
      </ThreadGroup>
      <hashTree>
        <HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="HTTP Request" enabled="true">
          <stringProp name="HTTPSampler.domain">example.com</stringProp>
          <stringProp name="HTTPSampler.protocol">https</stringProp>
          <stringProp name="HTTPSampler.path">/</stringProp>
          <stringProp name="HTTPSampler.method">GET</stringProp>
        </HTTPSamplerProxy>
        <hashTree/>
      </hashTree>
    </hashTree>
  </hashTree>
</jmeterTestPlan>
