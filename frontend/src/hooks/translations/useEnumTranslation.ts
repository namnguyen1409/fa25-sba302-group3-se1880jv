import { ENUM_TRANSLATIONS } from "./translationMaps";

export function useEnumTranslation() {
  /**
   * Dịch 1 enum value
   */
  const translate = (
    category: keyof typeof ENUM_TRANSLATIONS,
    value?: string | null
  ) => {
    if (!value) return "";
    const map = ENUM_TRANSLATIONS[category] as Record<string, string>;
    return map?.[value] ?? value;
  };

  /**
   * Lấy options để tạo select
   */
  const toOptions = (category: keyof typeof ENUM_TRANSLATIONS) => {
    const map = ENUM_TRANSLATIONS[category] as Record<string, string>;
    return Object.entries(map).map(([value, label]) => ({
      value,
      label,
    }));
  };

  /**
   * Kiểm tra category tồn tại hay không
   */
  const categories = Object.keys(ENUM_TRANSLATIONS);

  return { translate, toOptions, categories };
}
