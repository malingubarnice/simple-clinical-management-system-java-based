import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClinicManagementSystem {
    // Lists to store patients, doctors, bills, prescriptions, schedules, and appointments
    private static ArrayList<String> patientList = new ArrayList<>();
    private static ArrayList<String> doctorList = new ArrayList<>();
    private static ArrayList<Bill> billList = new ArrayList<>();
    private static ArrayList<Prescription> prescriptionList = new ArrayList<>();
    private static ArrayList<Schedule> scheduleList = new ArrayList<>();
    private static ArrayList<Appointment> appointmentList = new ArrayList<>();

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("NewLight Clinic Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create and set up the title label
        JLabel titleLabel = new JLabel("NewLight Clinic", SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 600, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create buttons with enlarged size
        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.setBounds(50, 60, 200, 50);
        addPatientButton.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton viewPatientButton = new JButton("View Patient");
        viewPatientButton.setBounds(50, 130, 200, 50);
        viewPatientButton.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton manageAppointmentsButton = new JButton("Manage Appointments");
        manageAppointmentsButton.setBounds(50, 200, 300, 50);
        manageAppointmentsButton.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton managePrescriptionsButton = new JButton("Manage Prescriptions");
        managePrescriptionsButton.setBounds(50, 270, 300, 50);
        managePrescriptionsButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add action listeners
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show input dialog to enter patient name
                String patientName = JOptionPane.showInputDialog(frame, "Enter Patient's Name:");
                if (patientName != null && !patientName.trim().isEmpty()) {
                    patientList.add(patientName);
                    JOptionPane.showMessageDialog(frame, "Patient added: " + patientName);
                } else {
                    JOptionPane.showMessageDialog(frame, "No patient name entered.");
                }
            }
        });

        viewPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the list of patients
                if (patientList.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No patients added yet.");
                } else {
                    StringBuilder patientNames = new StringBuilder("Patients:\n");
                    for (String name : patientList) {
                        patientNames.append(name).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, patientNames.toString());
                }
            }
        });

        manageAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a dialog with options for managing appointments
                String[] options = {"Doctor Booking", "Doctor Management", "Billing", "View Appointments"};
                int choice = JOptionPane.showOptionDialog(frame,
                        "Choose an option:",
                        "Manage Appointments",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                // Handle the choice
                switch (choice) {
                    case 0:
                        // Show options for Doctor Booking
                        String[] bookingOptions = {"Outpatient Booking", "Inpatient Booking", "Specialist Booking"};
                        int bookingChoice = JOptionPane.showOptionDialog(frame,
                                "Choose a booking type:",
                                "Doctor Booking",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                bookingOptions,
                                bookingOptions[0]);

                        // Handle the booking choice
                        switch (bookingChoice) {
                            case 0:
                                JOptionPane.showMessageDialog(frame, "Outpatient Booking selected.");
                                break;
                            case 1:
                                JOptionPane.showMessageDialog(frame, "Inpatient Booking selected.");
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(frame, "Specialist Booking selected.");
                                break;
                            default:
                                // Option was canceled or closed
                                break;
                        }

                        // Show dialog for Doctor Scheduling
                        String doctorName = JOptionPane.showInputDialog(frame, "Enter Doctor's Name for Scheduling:");
                        if (doctorName != null && !doctorName.trim().isEmpty()) {
                            String scheduleDetails = JOptionPane.showInputDialog(frame, "Enter Schedule Details:");
                            if (scheduleDetails != null && !scheduleDetails.trim().isEmpty()) {
                                scheduleList.add(new Schedule(doctorName, scheduleDetails));
                                JOptionPane.showMessageDialog(frame, "Schedule added for Dr. " + doctorName);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No schedule details entered.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "No doctor name entered.");
                        }

                        // Book appointment
                        String patientNameForAppointment = JOptionPane.showInputDialog(frame, "Enter Patient's Name for Appointment:");
                        if (patientNameForAppointment != null && !patientNameForAppointment.trim().isEmpty()) {
                            String doctorNameForAppointment = JOptionPane.showInputDialog(frame, "Enter Doctor's Name for Appointment:");
                            if (doctorNameForAppointment != null && !doctorNameForAppointment.trim().isEmpty()) {
                                String appointmentDetails = JOptionPane.showInputDialog(frame, "Enter Appointment Details:");
                                if (appointmentDetails != null && !appointmentDetails.trim().isEmpty()) {
                                    appointmentList.add(new Appointment(patientNameForAppointment, doctorNameForAppointment, appointmentDetails));
                                    JOptionPane.showMessageDialog(frame, "Appointment booked for " + patientNameForAppointment + " with Dr. " + doctorNameForAppointment);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "No appointment details entered.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(frame, "No doctor name entered.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "No patient name entered.");
                        }
                        break;
                    case 1:
                        // Show options for Doctor Management
                        String[] doctorManagementOptions = {"Add Doctor", "View Doctors", "Remove Doctor"};
                        int doctorManagementChoice = JOptionPane.showOptionDialog(frame,
                                "Choose an option:",
                                "Doctor Management",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                doctorManagementOptions,
                                doctorManagementOptions[0]);

                        // Handle the doctor management choice
                        switch (doctorManagementChoice) {
                            case 0:
                                // Add Doctor
                                String doctorNameToAdd = JOptionPane.showInputDialog(frame, "Enter Doctor's Name:");
                                if (doctorNameToAdd != null && !doctorNameToAdd.trim().isEmpty()) {
                                    doctorList.add(doctorNameToAdd);
                                    JOptionPane.showMessageDialog(frame, "Doctor added: " + doctorNameToAdd);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "No doctor name entered.");
                                }
                                break;
                            case 1:
                                // View Doctors
                                if (doctorList.isEmpty()) {
                                    JOptionPane.showMessageDialog(frame, "No doctors added yet.");
                                } else {
                                    StringBuilder doctorNames = new StringBuilder("Doctors:\n");
                                    for (String name : doctorList) {
                                        doctorNames.append(name).append("\n");
                                    }
                                    JOptionPane.showMessageDialog(frame, doctorNames.toString());
                                }
                                break;
                            case 2:
                                // Remove Doctor
                                String removeDoctorName = JOptionPane.showInputDialog(frame, "Enter Doctor's Name to Remove:");
                                if (removeDoctorName != null && !removeDoctorName.trim().isEmpty()) {
                                    if (doctorList.remove(removeDoctorName)) {
                                        JOptionPane.showMessageDialog(frame, "Doctor removed: " + removeDoctorName);
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Doctor not found: " + removeDoctorName);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(frame, "No doctor name entered.");
                                }
                                break;
                            default:
                                // Option was canceled or closed
                                break;
                        }
                        break;
                    case 2:
                        // Show options for Billing
                        String[] billingOptions = {"Add Bill", "View Bills", "Calculate Total Amount"};
                        int billingChoice = JOptionPane.showOptionDialog(frame,
                                "Choose an option:",
                                "Billing",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                billingOptions,
                                billingOptions[0]);

                        // Handle the billing choice
                        switch (billingChoice) {
                            case 0:
                                // Add Bill
                                String patientNameForBill = JOptionPane.showInputDialog(frame, "Enter Patient's Name:");
                                if (patientNameForBill != null && !patientNameForBill.trim().isEmpty()) {
                                    String amountStr = JOptionPane.showInputDialog(frame, "Enter Amount (KES):");
                                    try {
                                        double amount = Double.parseDouble(amountStr);
                                        billList.add(new Bill(patientNameForBill, amount));
                                        JOptionPane.showMessageDialog(frame, "Bill added for " + patientNameForBill + ": KES " + amount);
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(frame, "Invalid amount entered.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(frame, "No patient name entered.");
                                }
                                break;
                            case 1:
                                // View Bills
                                if (billList.isEmpty()) {
                                    JOptionPane.showMessageDialog(frame, "No bills added yet.");
                                } else {
                                    StringBuilder bills = new StringBuilder("Bills:\n");
                                    for (Bill bill : billList) {
                                        bills.append("Patient: ").append(bill.getPatientName())
                                                .append(", Amount: KES ").append(bill.getAmount()).append("\n");
                                    }
                                    JOptionPane.showMessageDialog(frame, bills.toString());
                                }
                                break;
                            case 2:
                                // Calculate Total Amount
                                double totalAmount = 0;
                                for (Bill bill : billList) {
                                    totalAmount += bill.getAmount();
                                }
                                JOptionPane.showMessageDialog(frame, "Total Amount Due: KES " + totalAmount);
                                break;
                            default:
                                // Option was canceled or closed
                                break;
                        }
                        break;
                    case 3:
                        // View Appointments
                        if (appointmentList.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "No appointments booked yet.");
                        } else {
                            StringBuilder appointments = new StringBuilder("Appointments:\n");
                            for (Appointment appointment : appointmentList) {
                                appointments.append("Patient: ").append(appointment.getPatientName())
                                        .append(", Doctor: ").append(appointment.getDoctorName())
                                        .append(", Details: ").append(appointment.getDetails()).append("\n");
                            }
                            JOptionPane.showMessageDialog(frame, appointments.toString());
                        }
                        break;
                    default:
                        // Option was canceled or closed
                        break;
                }
            }
        });

        managePrescriptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a dialog with options for managing prescriptions
                String[] options = {"Add Prescription", "View Prescriptions"};
                int choice = JOptionPane.showOptionDialog(frame,
                        "Choose an option:",
                        "Manage Prescriptions",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                // Handle the choice
                switch (choice) {
                    case 0:
                        // Add Prescription
                        String patientName = JOptionPane.showInputDialog(frame, "Enter Patient's Name:");
                        if (patientName != null && !patientName.trim().isEmpty()) {
                            String prescriptionDetails = JOptionPane.showInputDialog(frame, "Enter Prescription Details:");
                            if (prescriptionDetails != null && !prescriptionDetails.trim().isEmpty()) {
                                prescriptionList.add(new Prescription(patientName, prescriptionDetails));
                                JOptionPane.showMessageDialog(frame, "Prescription added for " + patientName);
                            } else {
                                JOptionPane.showMessageDialog(frame, "No prescription details entered.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "No patient name entered.");
                        }
                        break;
                    case 1:
                        // View Prescriptions
                        if (prescriptionList.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "No prescriptions added yet.");
                        } else {
                            StringBuilder prescriptions = new StringBuilder("Prescriptions:\n");
                            for (Prescription prescription : prescriptionList) {
                                prescriptions.append("Patient: ").append(prescription.getPatientName())
                                        .append(", Details: ").append(prescription.getDetails()).append("\n");
                            }
                            JOptionPane.showMessageDialog(frame, prescriptions.toString());
                        }
                        break;
                    default:
                        // Option was canceled or closed
                        break;
                }
            }
        });

        // Add components to the frame
        frame.add(titleLabel);
        frame.add(addPatientButton);
        frame.add(viewPatientButton);
        frame.add(manageAppointmentsButton);
        frame.add(managePrescriptionsButton);

        // Make frame visible
        frame.setVisible(true);
    }

    // Inner class to represent a bill
    static class Bill {
        private String patientName;
        private double amount;

        public Bill(String patientName, double amount) {
            this.patientName = patientName;
            this.amount = amount;
        }

        public String getPatientName() {
            return patientName;
        }

        public double getAmount() {
            return amount;
        }
    }

    // Inner class to represent a prescription
    static class Prescription {
        private String patientName;
        private String details;

        public Prescription(String patientName, String details) {
            this.patientName = patientName;
            this.details = details;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getDetails() {
            return details;
        }
    }

    // Inner class to represent a schedule
    static class Schedule {
        private String doctorName;
        private String details;

        public Schedule(String doctorName, String details) {
            this.doctorName = doctorName;
            this.details = details;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public String getDetails() {
            return details;
        }
    }

    // Inner class to represent an appointment
    static class Appointment {
        private String patientName;
        private String doctorName;
        private String details;

        public Appointment(String patientName, String doctorName, String details) {
            this.patientName = patientName;
            this.doctorName = doctorName;
            this.details = details;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public String getDetails() {
            return details;
        }
    }
}
