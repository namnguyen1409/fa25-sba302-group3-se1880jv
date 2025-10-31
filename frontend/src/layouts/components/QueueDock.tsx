import { useEffect, useState } from "react";
import { Card } from "@/components/ui/card";

export const QueueDock = () => {
  const [queue, setQueue] = useState([]);

  // TODO: subscribe WS event "queue:update"
  useEffect(() => {}, []);

  if (queue.length === 0) {
    return null;
  }

  return (
    <Card className="h-16 border-t bg-white dark:bg-gray-800 flex items-center px-4 gap-4">
      <span className="font-medium">Queue:</span>
      <div className="flex gap-2 overflow-x-auto">
        {queue.map((p, i) => (
          <span key={i} className="px-2 py-1 bg-primary/10 rounded text-xs whitespace-nowrap">
            {/* {p.patientName} */}
          </span>
        ))}
      </div>
    </Card>
  );
};
