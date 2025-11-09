import {
  LayoutDashboard,
  Users,
  ClipboardList,
  Stethoscope,
  HeartPulse,
  Syringe,
  FlaskRound,
  Pill,
  Receipt,
  CalendarCheck,
  Building2,
  ShieldCheck,
  Settings,
  UserPlus,
  ClipboardCheck,
  Wallet,
  Activity,
  MonitorCheck,
  BookOpenCheck,
  Box
} from "lucide-react";

export const staffNavItems = (roles: string[]) => {
  const base = [
    // { label: "Dashboard", to: "/staff/dashboard", icon: LayoutDashboard },
    { label: "My Schedule", to: "/staff/schedule", icon: CalendarCheck },
  ];

  const receptionist = [
    { label: "Appointments", to: "/staff/appointments", icon: CalendarCheck },
    { label: "Patients", to: "/staff/patients", icon: Users },
    { label: "Queue", to: "/staff/queue", icon: ClipboardList },
  ];

  const nurse = [
    { label: "Patient Intake", to: "/staff/intake", icon: ClipboardCheck },
    { label: "Vitals", to: "/staff/vitals", icon: HeartPulse },
    { label: "Queue", to: "/staff/queue", icon: ClipboardList },
  ];

  const doctor = [
    
    { label: "My Patients", to: "/staff/patients", icon: Users },
    { label: "Examinations", to: "/staff/examinations", icon: Stethoscope },
    { label: "Queue", to: "/staff/queue", icon: ClipboardList },
  ];

  const technician = [
    { label: "Service Orders", to: "/staff/service-orders", icon: Box },
    { label: "Queue", to: "/staff/service-queue", icon: ClipboardList },
  ];

  const labTechnician = [
    { label: "Queue", to: "/staff/lab-orders", icon: FlaskRound },
    { label: "Lab Orders", to: "/staff/lab-orders-history", icon: ClipboardList },
  ];


  const pharmacist = [
    { label: "Dispense", to: "/staff/prescriptions", icon: Syringe },
    { label: "History", to: "/staff/dispense-history", icon: Wallet },
  ];

  const cashier = [
    { label: "Billing Queue", to: "/staff/billing/queue", icon: Receipt },
    { label: "History", to: "/staff/history", icon: Wallet },
  ];

  const manager = [
    { label: "Staff", to: "/staff/staffs", icon: UserPlus },
    { label: "Positions", to: "/staff/positions", icon: ClipboardList },
    { label: "Departments", to: "/staff/departments", icon: Building2 },
    { label: "Rooms", to: "/staff/rooms", icon: Building2 },
    { label: "Reports", to: "/staff/reports", icon: Activity },
  ];


  const catalog = [
    { label: "ICD Codes", to: "/staff/icd", icon: BookOpenCheck },
    { label: "Medicines", to: "/staff/medications", icon: Pill },
    { label: "Services", to: "/staff/service-catalogs", icon: Box },
    { label: "Lab Tests", to: "/staff/lab-tests", icon: FlaskRound },
  ];


  const sysadmin = [
    { label: "User Management", to: "/staff/users", icon: ShieldCheck },
    { label: "System Settings", to: "/staff/settings", icon: Settings },
    { label: "Device Access Logs", to: "/staff/devices", icon: MonitorCheck },
  ];

  const specialty = [
    { label: "Specialties", to: "/staff/specialties", icon: Stethoscope },
  ];

  const map = {
    ROLE_RECEPTIONIST: receptionist,
    ROLE_NURSE: nurse,
    ROLE_DOCTOR: doctor,
    ROLE_TECHNICIAN: technician,
    ROLE_LAB_TECHNICIAN: labTechnician,
    ROLE_PHARMACIST: pharmacist,
    ROLE_CASHIER: cashier,
    ROLE_MANAGER: [
      ...manager,
      ...specialty,
      ...catalog,
    ],
    ROLE_SYSTEM_ADMIN: [...manager, ...catalog, ...sysadmin, ...specialty],
  };

  return [
    ...base,
    ...roles.flatMap(r => map[r as keyof typeof map] || []),
  ];
};
