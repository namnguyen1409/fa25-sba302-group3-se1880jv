declare global {
  interface Set<T> {
    map<R>(fn: (v: T) => R): R[];
    find(fn: (v: T) => boolean): T | undefined;
  }
}

export {};
