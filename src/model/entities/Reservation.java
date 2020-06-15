package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exception.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkIn.before(checkOut)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		if (!checkIn.before(checkOut)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		if (!checkIn.after(new Date()) || !checkOut.after(new Date())) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room " + roomNumber + ", check-in: ");
		sb.append(sdf.format(checkIn) + ", check-out: ");
		sb.append(sdf.format(checkOut) + ", " + duration() + " nights");
		
		return sb.toString();
	}
}
