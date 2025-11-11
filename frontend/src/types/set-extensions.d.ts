declare global {
  interface Set<T> {
    map<R>(fn: (v: T, idx: number) => R): R[];
    find(fn: (v: T) => boolean): T | undefined;
    length: number;
  }
}

export {};
