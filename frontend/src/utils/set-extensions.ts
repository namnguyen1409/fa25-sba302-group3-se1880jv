
if (!Set.prototype.map) {
  Set.prototype.map = function <T, R>(this: Set<T>, fn: (v: T) => R): R[] {
    return Array.from(this).map(fn);
  };
}

if (!Set.prototype.find) {
  Set.prototype.find = function <T>(
    this: Set<T>,
    fn: (v: T) => boolean
  ): T | undefined {
    return Array.from(this).find(fn);
  };
}
