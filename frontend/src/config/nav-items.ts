import { Stethoscope, FlaskRound, Pill, Receipt, Users } from "lucide-react";

export const staffNavItems = (roles: string[]) => {
  const base = [
    { label: "Dashboard", to: "/staff", icon: Users },
    { label: "Queue", to: "/staff/queue", icon: Users },
    { label: "Patients", to: "/staff/patients", icon: Users },
  ];

  const doctor = [
    { label: "My Appointments", to: "/staff/appointments", icon: Stethoscope },
    { label: "Examinations", to: "/staff/examinations", icon: Stethoscope },
    { label: "Lab Orders", to: "/staff/lab-requests", icon: FlaskRound },
  ];

  const lab = [
    { label: "Lab Orders", to: "/staff/lab-requests", icon: FlaskRound },
  ];

  const pharmacy = [
    { label: "Prescriptions", to: "/staff/prescriptions", icon: Pill },
    { label: "Dispensing", to: "/staff/dispense", icon: Pill },
  ];

  const billing = [
    { label: "Billing", to: "/staff/billing", icon: Receipt },
  ];

  const byRole: Record<string, any[]> = {
    DOCTOR: doctor,
    NURSE: doctor, // nurses share core functions
    LAB: lab,
    PHARMACY: pharmacy,
    CASHIER: billing,
    ROLE_ADMIN: [...doctor, ...lab, ...pharmacy, ...billing],
  };

  return [...base, ...(roles.flatMap(role => byRole[role] || []))];
};
