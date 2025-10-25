export const DEVICE_ID_KEY = "device_id";

export function ensureDeviceId(): string {
  let deviceId = localStorage.getItem(DEVICE_ID_KEY);
  if (!deviceId) {
    deviceId = crypto.randomUUID();
    localStorage.setItem(DEVICE_ID_KEY, deviceId);
    console.info("Device ID generated:", deviceId);
  }
  return deviceId;
}
