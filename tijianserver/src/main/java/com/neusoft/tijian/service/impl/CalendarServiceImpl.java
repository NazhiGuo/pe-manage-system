package com.neusoft.tijian.service.impl;

import com.neusoft.tijian.dto.CalendarRequestDto;
import com.neusoft.tijian.dto.CalendarResponseDto;
import com.neusoft.tijian.dto.OrdersMapperDto;
import com.neusoft.tijian.mapper.HospitalMapper;
import com.neusoft.tijian.mapper.OrdersMapper;
import com.neusoft.tijian.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    // 生成预约日历
    @Override
    public List<CalendarResponseDto> listAppointmentCalendar(CalendarRequestDto dto) {
        // 获取预约日期列表
        List<CalendarResponseDto> calendarList30 = getCalendarList30(dto.getHpId());
        // 获取当前日历（当前日历中只有ymd属性，total、existing、remainder需要从calendarList30获取）
        List<CalendarResponseDto> currentCalendarList = getCurrentCalendarList(dto.getYear(),dto.getMonth());

        // 给当前日历填充total、existing、remainder属性
        // 使用一个嵌套循环来读取对应的日历信息
        // 先是把当前的日历读出来
        // 然后把可以预约的 30 天也拿出来
        // 然后填充total、existing、remainder属性
        for(CalendarResponseDto current : currentCalendarList) {
            for(CalendarResponseDto calendar : calendarList30) {
                if(current.getYmd()!=null) {
                    if(current.getYmd().equals(calendar.getYmd())) {
                        current.setTotal(calendar.getTotal());
                        current.setExisting(calendar.getExisting());
                        current.setRemainder(calendar.getRemainder());
                    }
                }
            }
        }
        return currentCalendarList;
    }

    // 第一步：获取 30 天预约日期列表
    private List<CalendarResponseDto> getCalendarList30(Integer hpId){
        // 设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();

        // 做一个临时集合，作为去 Orders 表中查询的参数
        List<OrdersMapperDto> parameList = new ArrayList<>();
        // 因为只能预约今天开始起之后的 30 天，所以通过循环设置 30 天
        for(int i=0;i<30;i++) {
            // 使用 OrdersMapperDto 作为订单中数据传递的对象
            OrdersMapperDto dto = new OrdersMapperDto();
            // 每循环一次，都要往后多计算 1 天的时间
            calendar.add(Calendar.DATE, 1);
            // 然后开始设置
            dto.setDate(sdf.format(calendar.getTime()));
            dto.setHpId(hpId);
            // 将设置好的内容，添加到临时集合中
            parameList.add(dto);
        }

        // 根据 parameList 参数，查询 30 天预约日期中，每一天的已预约人数（先在 OrdersMapper 中实现，拿到数据先）
        // 查询结果 CalendarResponseDto 中只有两个属性：ymd、existing，还有两个属性需要填充：total、remainder
        List<CalendarResponseDto> calendarList30 = ordersMapper.listOrdersAppointmentNumber(parameList);

        // 根据医院编号，获取预约规则，就能获取每天最多预约人数
        // 需要在 hospitalMapper 中提供 getHospitalById() 来实现
        // 然后将预约规则，拆开放在数组中
        String[] strArr = hospitalMapper.getHospitalById(hpId).getRule().split(",");

        // 继续填充 calendarList30 返回值中的 total、remainder 属性
        for(CalendarResponseDto cd : calendarList30) {
            // 将预约日期转换为 Calendar 对象
            try {
                calendar.setTime(sdf.parse(cd.getYmd()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 通过 Calendar 对象的 get 方法获取某天为星期几，就可以通过预约规则数组获取某天的总预约人数
            int total = Integer.parseInt(strArr[calendar.get(Calendar.DAY_OF_WEEK)-1]);
            // 填充 total 属性
            cd.setTotal(total);
            // 填充 remainder 属性，也就是剩余预约人数
            cd.setRemainder(total-cd.getExisting());
        }
        // 返回结果
        return calendarList30;
    }

    // 第二步：获取当前年和当前月的日历
    private List<CalendarResponseDto> getCurrentCalendarList(Integer year,Integer month){
        List<CalendarResponseDto> currentCalendar = new ArrayList<>();

        // 实现万年历（下面的代码，都是非必要掌握的，了解日期的处理即可）
        boolean isRun = false;
        if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            isRun = true;
        }
        // 计算从 1900-01-01 到现在的天数
        int totalDays = 0;
        for(int i=1900;i<year;i++) {
            if((i%4==0&&i%100!=0) || (i%400==0)) {
                totalDays += 366;
            }else {
                totalDays += 365;
            }
        }
        int beforeDays = 0;
        int currentDays = 0;
        for(int j=1;j<=month;j++) {
            switch(j) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    currentDays = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    currentDays = 30;
                    break;
                case 2:
                    if(isRun) {
                        currentDays = 29;
                    }else {
                        currentDays = 28;
                    }
                    break;
            }
            if(j<month) {
                beforeDays += currentDays;
            }
        }
        totalDays += beforeDays;
        int firstDayOfMonth = totalDays%7 + 1;
        if(firstDayOfMonth == 7) {
            firstDayOfMonth = 0;
        }

        // 很重要的两点：
        // 1、填充日历空白值
        for(int k=0;k<firstDayOfMonth;k++) {
            currentCalendar.add(new CalendarResponseDto());
        }
        // 2、填充本月后面的天数
        for(int i=1;i<=currentDays;i++) {
            String m = month < 10 ? "0" + month : month + "";
            String d = i < 10 ? "0" + i : i + "";
            currentCalendar.add(new CalendarResponseDto(year+"-"+m+"-"+d));
        }

        // 返回当前日历
        return currentCalendar;
    }

}
