import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  ResponsiveContainer,
  Tooltip,
  PieChart,
  Pie,
  Cell,
  LineChart,
  Line,
} from "recharts";
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card";

const COLORS = [
  "#4f46e5",
  "#10b981",
  "#f97316",
  "#6366f1",
  "#ec4899",
  "#14b8a6",
  "#a855f7",
];

export function StatsCard({ title, value }: { title: string; value: any }) {
  return (
    <Card className="p-3">
      <CardTitle className="text-sm">{title}</CardTitle>
      <div className="text-xl font-bold mt-1">{value}</div>
    </Card>
  );
}

export function BarChartBox({
  data,
  xKey,
  dataKey,
}: {
  data: any[];
  xKey: string;
  dataKey: string;
}) {
  return (
    <ResponsiveContainer width="100%" height={300}>
      <BarChart data={data}>
        <XAxis dataKey={xKey} />
        <YAxis />
        <Tooltip />
        <Bar dataKey={dataKey} />
      </BarChart>
    </ResponsiveContainer>
  );
}

export function LineChartBox({
  data,
  xKey,
  dataKey,
}: {
  data: any[];
  xKey: string;
  dataKey: string;
}) {
  return (
    <ResponsiveContainer width="100%" height={300}>
      <LineChart data={data}>
        <XAxis dataKey={xKey} />
        <YAxis />
        <Tooltip />
        <Line type="monotone" dataKey={dataKey} stroke="#4f46e5" />
      </LineChart>
    </ResponsiveContainer>
  );
}

export function PieChartBox({ data }: { data: any[] }) {
  return (
    <ResponsiveContainer width="100%" height={300}>
      <PieChart>
        <Pie
          dataKey="value"
          data={data}
          cx="50%"
          cy="50%"
          outerRadius={100}
          label
        >
          {data.map((_, idx) => (
            <Cell key={idx} fill={COLORS[idx % COLORS.length]} />
          ))}
        </Pie>
        <Tooltip />
      </PieChart>
    </ResponsiveContainer>
  );
}
