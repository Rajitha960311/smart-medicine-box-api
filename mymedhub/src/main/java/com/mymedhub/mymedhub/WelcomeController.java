package com.mymedhub.mymedhub;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymedhub.mymedhub.entity.MedInfoDetail;
import com.mymedhub.mymedhub.entity.MedInfoMaster;
import com.mymedhub.mymedhub.service.MedHubService;


@RestController
public class WelcomeController {
	
	@Autowired
	private MedHubService service;

	@GetMapping("/")
	public String welcome() {
		return "welcome to my API";
			
	}
	
	@PostMapping(value = "/medInfo")
	public String saveMedInfo(@RequestBody String medInfo) throws ParseException {
		System.out.println(medInfo);
		//String medInfo = "/101/Vitamin/Capsule/After meal/50/No/8:30 a.m./1/3:00 p.m./1/9:00 p.m/2";
		String[] result = medInfo.split("/");
		
		
		for (int i = 1; i < result.length; i++) {
			System.out.println(result[i]);	
		}
		
		MedInfoMaster medInfoMaster = new MedInfoMaster();
		medInfoMaster.setRfid(result[1]);
		medInfoMaster.setMedName(result[2]);
		medInfoMaster.setUnit(result[3]);
		medInfoMaster.setIntakeAdvice(result[4]);
		medInfoMaster.setInventory(Integer.valueOf(result[5]));
		
		if(result[6].equalsIgnoreCase("Yes"))
			medInfoMaster.setAsNeeded(true);
		else
			medInfoMaster.setAsNeeded(false);
		
		List<MedInfoDetail> ls = new ArrayList<MedInfoDetail>();
		
		MedInfoDetail medInfoDetail1 = new MedInfoDetail();
		medInfoDetail1.setMedInfoMaster(medInfoMaster);
		medInfoDetail1.setTime(StringToTime(result[7]).toString());
		medInfoDetail1.setDosage(Integer.valueOf(result[8]));
		
		MedInfoDetail medInfoDetail2 = new MedInfoDetail();
		medInfoDetail2.setMedInfoMaster(medInfoMaster);
		medInfoDetail2.setTime(StringToTime(result[9]).toString());
		medInfoDetail2.setDosage(Integer.valueOf(result[10]));
		
		MedInfoDetail medInfoDetail3 = new MedInfoDetail();
		medInfoDetail3.setMedInfoMaster(medInfoMaster);
		medInfoDetail3.setTime(StringToTime(result[11]).toString());
		medInfoDetail3.setDosage(Integer.valueOf(result[12]));
		
		ls.add(medInfoDetail1);
		ls.add(medInfoDetail2);
		ls.add(medInfoDetail3);
		
		service.saveMedInfoMaster(medInfoMaster);
		service.saveMedInfoDetail(ls);
		
		

		return "success";
		
	}
	
	public LocalTime StringToTime(String input) {
		
		String rs[] = input.split(" ");
		if(rs[1].equals("a.m."))
			rs[0] = rs[0]+" AM";
		else
			rs[0] = rs[0]+" PM";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "h:m a" , Locale.ENGLISH );
		LocalTime localTime = LocalTime.parse ( rs[0] , formatter );
		return localTime;
	}
	
	@GetMapping("/medInfo/{id}")
	public String getMedInfo(@PathVariable String id) throws ParseException {
		
		System.out.println("Tag ID :"+id);
		String msg="a";
		String remindMsg = "";
		
		try {
			Optional<MedInfoMaster> medInfoMaster = service.findByID(id);
			List<MedInfoDetail> medInfoDetailList = service.getReminderTimes(id);
			String medName = medInfoMaster.get().getMedName();
			String intakeAdvice = medInfoMaster.get().getIntakeAdvice();
			int inventory = medInfoMaster.get().getInventory();
			String unit = medInfoMaster.get().getUnit();
			boolean a = medInfoMaster.get().isAsNeeded();
			String asNeeded = "";
			if(a == true)
				asNeeded="Yes";
			else
				asNeeded="No";
			
			//msg = medName+" "+unit+" "+intakeAdvice+" "+inventory+" "+asNeeded;
			
			SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
			DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
			
			Date currentTime = tf.parse(tf.format(new Date()));
			String reminderTime=""; int dosage=0;
			for(MedInfoDetail medDetail :medInfoDetailList) {
				
				Date reminder = tf.parse(medDetail.getTime());
				if(reminder.compareTo(currentTime)==0) {
					//remindMsg = "Please take "+medDetail.getDosage()+" "+unit+" now";
					msg = "Please take now "+medDetail.getDosage()+" "+unit;
				}
				
				if(reminder.compareTo(currentTime)>0) {
					//remindMsg = "The next dose is at " +outputformat.format(reminder);
					reminderTime = outputformat.format(reminder);
					dosage = medDetail.getDosage();
					msg = "This is the "+medName+" bottle please take "+dosage+" "+unit+" "+intakeAdvice+" at "+reminderTime;
					break;
				}
			}
			
			//msg = msg +" "+remindMsg;	
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
 
		System.out.println(msg);
		return msg;
	   
	 }
	
	@GetMapping("/medInfo/alarm")
	public String checkReminders() throws ParseException  {
		
		System.out.println("calling check reminders");
		String haveReminder = "false";
		
		try {

			List<MedInfoDetail> medInfoDetailList = service.getAllReminderTimes();
			
			SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
			
			Date currentTime = tf.parse(tf.format(new Date()));
			
			for(MedInfoDetail medDetail :medInfoDetailList) {
				
				Date reminder = tf.parse(medDetail.getTime());
				
				if(reminder.compareTo(currentTime)==0) {
					haveReminder = "true";
				}
				

			}			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(haveReminder);
		return haveReminder;
		
	}
	
}
