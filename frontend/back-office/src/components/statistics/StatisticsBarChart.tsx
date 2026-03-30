type StatisticsEntry = {
  label: string;
  value: number;
};

interface StatisticsBarChartProps {
  title: string;
  data: StatisticsEntry[];
}

export default function StatisticsBarChart({
  title,
  data,
}: StatisticsBarChartProps) {
  const maxValue = Math.max(...data.map((item) => item.value), 1);

  return (
    <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
      <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
        {title}
      </h3>

      <div className="mt-6 flex min-h-[260px] items-end justify-between gap-4 overflow-x-auto">
        {data.map((item) => {
          const heightPercent = (item.value / maxValue) * 100;

          return (
            <div
              key={item.label}
              className="flex min-w-[90px] flex-1 flex-col items-center justify-end"
            >
              <span className="mb-2 text-sm font-semibold text-gray-700 dark:text-gray-300">
                {item.value}
              </span>

              <div className="flex h-[180px] items-end">
                <div
                  className="w-14 rounded-t-xl bg-brand-500 transition-all duration-300"
                  style={{ height: `${heightPercent}%` }}
                />
              </div>

              <span className="mt-3 text-center text-xs text-gray-500 dark:text-gray-400">
                {item.label}
              </span>
            </div>
          );
        })}
      </div>
    </div>
  );
}