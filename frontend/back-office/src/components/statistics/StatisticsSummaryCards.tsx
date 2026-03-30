type StatisticsEntry = {
  label: string;
  value: number;
};

interface StatisticsHorizontalChartProps {
  title: string;
  data: StatisticsEntry[];
}

export default function StatisticsHorizontalChart({
  title,
  data,
}: StatisticsHorizontalChartProps) {
  const maxValue = Math.max(...data.map((item) => item.value), 1);

  return (
    <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
      <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
        {title}
      </h3>

      <div className="mt-6 space-y-4">
        {data.map((item) => {
          const widthPercent = (item.value / maxValue) * 100;

          return (
            <div key={item.label}>
              <div className="mb-2 flex items-center justify-between gap-3">
                <span className="text-sm font-medium text-gray-700 dark:text-gray-300">
                  {item.label}
                </span>
                <span className="text-sm font-semibold text-gray-800 dark:text-white">
                  {item.value}
                </span>
              </div>

              <div className="h-4 w-full rounded-full bg-gray-100 dark:bg-gray-800">
                <div
                  className="h-4 rounded-full bg-brand-500 transition-all duration-300"
                  style={{ width: `${widthPercent}%` }}
                />
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}