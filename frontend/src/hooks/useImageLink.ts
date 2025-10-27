import { API_BASE_URL } from "@/api/axiosInstance";

export const useImageLink = (imagePath: string) => {
    if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
        return imagePath;
    }
    return `${API_BASE_URL.replace("/api", "")}${imagePath}`;
}
