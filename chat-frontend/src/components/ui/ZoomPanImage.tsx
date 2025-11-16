import React, { useRef, useEffect, useState } from 'react';

interface ZoomPanImageProps {
  src: string;
  alt?: string;
  className?: string;
}

export const ZoomPanImage: React.FC<ZoomPanImageProps> = ({ src, alt, className }) => {
  const imgRef = useRef<HTMLImageElement>(null);
  const containerRef = useRef<HTMLDivElement>(null);
  const [dragging, setDragging] = useState(false);
  const [origin, setOrigin] = useState<{ x: number; y: number }>({ x: 0, y: 0 });
  const [translate, setTranslate] = useState<{ x: number; y: number }>({ x: 0, y: 0 });
  const [scale, setScale] = useState(1);

  useEffect(() => {
    setTranslate({ x: 0, y: 0 });
    setScale(1);
  }, [src]);

  const handleWheel = (e: React.WheelEvent) => {
    e.preventDefault();
    setScale(s => Math.max(0.2, Math.min(5, s - e.deltaY * 0.001)));
  };

  const handleMouseDown = (e: React.MouseEvent) => {
    setDragging(true);
    setOrigin({ x: e.clientX - translate.x, y: e.clientY - translate.y });
  };

  const handleMouseMove = (e: React.MouseEvent) => {
    if (!dragging) return;
    setTranslate({ x: e.clientX - origin.x, y: e.clientY - origin.y });
  };

  const handleMouseUp = () => setDragging(false);

  return (
    <div
      ref={containerRef}
      className={"relative overflow-hidden bg-black flex items-center justify-center " + (className || "")}
      style={{ width: '100%', height: '80vh', cursor: dragging ? 'grabbing' : 'grab' }}
      onWheel={handleWheel}
      onMouseDown={handleMouseDown}
      onMouseMove={handleMouseMove}
      onMouseUp={handleMouseUp}
      onMouseLeave={handleMouseUp}
    >
      <img
        ref={imgRef}
        src={src}
        alt={alt}
        draggable={false}
        style={{
          transform: `translate(${translate.x}px, ${translate.y}px) scale(${scale})`,
          transition: dragging ? 'none' : 'transform 0.2s',
          maxWidth: 'unset',
          maxHeight: 'unset',
          userSelect: 'none',
          pointerEvents: 'all',
        }}
        className="select-none"
      />
    </div>
  );
};
