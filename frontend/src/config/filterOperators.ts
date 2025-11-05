export const operatorsByType = {
  number: ["eq","ne","gt","lt","ge","le","isNull","isNotNull"],
  date: ["eq","ne","gt","lt","ge","le","isNull","isNotNull"],
  boolean: ["eq","ne"],
  select: ["eq","ne","in"],
  text: [
    "contains","containsIgnoreCase","startsWith","startsWithIgnoreCase",
    "endsWith","endsWithIgnoreCase","eq","ne","isNull","isNotNull"
  ],
};

export const operatorLabels: Record<string,string> = {
  eq: "= Equals",
  ne: "≠ Not Equals",
  gt: "> Greater",
  lt: "< Less",
  ge: "≥ Greater or Equal",
  le: "≤ Less or Equal",
  contains: "Contains",
  containsIgnoreCase: "Contains Ignore Case",
  startsWith: "Starts With",
  startsWithIgnoreCase: "Starts With Ignore Case",
  endsWith: "Ends With",
  endsWithIgnoreCase: "Ends With Ignore Case",
  isNull: "Is Null",
  isNotNull: "Is Not Null",
  in: "In List",
};
