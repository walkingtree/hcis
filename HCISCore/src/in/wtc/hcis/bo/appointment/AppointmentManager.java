/**
 * 
 */
package in.wtc.hcis.bo.appointment;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Date;
import java.util.List;

/**
 * This interface provides the API for the following 
 * 			> Adding a new appointment
 * 			> Getting the appointment details
 * 			> Rescheduling an appointment
 * 			> Cancellation of an appointment
 * 			> Removing a Appointment from the Database
 * 			> Modifying an appointment details
 * 
 * The add and modify methods should take care in adding a new entry in the appointment history table
 * @author Rohit
 *
 */
public interface AppointmentManager 
{
	/**
	 * While adding an appointment the doctor slot has to be checked whether it is free or not
	 * after the booking is done that slot should be made free.
	 * A option can be given for the patients preference that if the slot selected by him is not free then the next available slot 
	 * 		can be allocated to him for that appointment.
	 * while adding an appointment the patient should also be able to configure reminders for the particular appointment.
	 * @param appointmentBM
	 * @throws HCISException
	 */	
	AppointmentBM addAppointment( AppointmentBM appointmentBM ) throws HCISException;
	
	/**
	 * 
	 * @param appointmentFromDate
	 * @param appointmentToDate
	 * @param patientId
	 * @param firstName
	 * @param lastName
	 * @param doctorName
	 * @param appointmentStatus
	 * @param bookingType
	 * @param timeFrom
	 * @param timeTo
	 * @return						---> A list of appointments BM
	 * @throws HCISException
	 */
	List<AppointmentBM> getAppointments( Date appointmentFromDate,
										Date appointmentToDate,
										Integer patientId,
										String patientName,
										String doctorName,
										String appointmentStatus,
										String bookingType,
										String timeFrom,
										String timeTo ) throws HCISException;
	
	
	/**
	 * 
	 * @param appointmentNumber
	 * @return
	 * @throws HCISException
	 */
	AppointmentBM getAppointment( Integer appointmentNumber ) throws HCISException;
	
	/**
	 * This method returns all the slots booked under a given roster code. This will 
	 * allow user interface to derive logic for showing available and booked slots for a 
	 * given roster.
	 * @param rosterId
	 * @return
	 * @throws HCISException
	 */
	List<AppointmentBM> getAppointmentsForARoster( Integer rosterId ) throws HCISException;
	
	/**
	 * When rescheduling it has to be checked that the slot of the doctor is free .
	 * On rescheduling the status of the old appointment has to be changed to reschedule and a new appointment number has to be allocated.
	 * Schedule anyway flag will indicate if the system should assign the first available slot for the given roster.
	 * @param oldAppointmentBM
	 * @param newAppointmentBM
	 * @return
	 * @throws HCISException
	 */
	AppointmentBM rescheduleAppointment( AppointmentBM oldAppointmentBM,
										 AppointmentBM newAppointmentBM,
										 Boolean scheduleAnywayFlag ) throws HCISException;
	/**
	 * When an appointment is cancelled we have to make the status of the appointment as cancelled.
	 * The doctor slot has to be made free for that time slot.
	 * @param appointmentBM
	 * @return
	 * @throws HCISException
	 */
	boolean cancelAppointment( AppointmentBM appointmentBM ) throws HCISException;
	
	/**
	 * When removing an appointment it should be checked that all services related to the appointment should also be removed.
	 *  
	 * @param appointmentBMlist
	 * @return
	 * @throws HCISException
	 */
	boolean removeAppointments( List<AppointmentBM>appointmentBMlist ) throws HCISException;
	
	/**
	 * When modifying the status of the appointment an entry should also go into the appointment history
	 * @param modifiedAppointmentBM
	 * @return
	 * @throws HCISException
	 */
	AppointmentBM modifyAppointment( AppointmentBM modifiedAppointmentBM ) throws HCISException;
	
	String generateVisitSlip(Integer appointmentNbr) throws HCISException;
	
	/**
	 * this method generates the doctor consultation fee receipt slip.
	 * @param appointmentNumber
	 * @return
	 * @throws HCISException
	 */
    String generatePatientConsultationReceiptSlip( Integer appointmentNumber )throws HCISException;
    
    /**
     *  updates appointment status by specifing the new status.
     * @param appointmentNumber
     * @param newStatus
     * @throws HCISException
     */
    void UpdateAppointmentStatus( Integer appointmentNumber, String newStatus)throws HCISException;
    
    /**
     *  It gives the all the available appointment numbers for followup
     *  by taking the patientid , docotorid .
     * @param patientId,doctorId
     * @param appointmentNumber
     * @throws HCISException
     */
    
    public ListRange getAppointmentForFollowUps(Integer patientId, Integer doctorId,int start, int count,String orderBy) throws HCISException;
    
    /**
     *  It gives the number of followups available for the given appointment number
     * @param appointmentNumber,patientId,doctorId
     * @param followUps
     * @throws HCISException
     */
    
    public Integer getNumberOfFollowUps(Integer patientId, Integer doctorId,Integer appointmentNo,Date appointmentDate) throws HCISException;
}
