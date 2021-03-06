import Measurement from '../models/Measurement';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);


function SensorComponent(measurements: Measurement[]) {
  const measurements2 = measurements.map((m) => {
    m.datetime = new Date(m.datetime);
    return m;
  })

  // Sort on measurement date
  measurements2.sort((a, b) => a.datetime.valueOf() - b.datetime.valueOf());

  const dates: string[] = measurements2.map((m) => m.datetime.toLocaleTimeString('nl-NL'))
  const temps: number[] = measurements2.map((m) => m.temperature)
  const brightness: number[] = measurements2.map((m) => m.brightness)

  const options = {
    responsive: true,
    scales: {
      x: {
        display: true,
        title: {
          display: true,
          text: 'Time'
        }
      },
      y: {
        display: true,
        beginAtZero: true,
        title: {
          display: true,
          text: 'Sensor_Measurements'
        }
      }
    },
    plugins: {
      legend: {
        position: 'top' as const,
      },
      title: {
        display: true,
        text: 'Measurement Line Chart',
      },
    },
  };

  const data = {
    labels: dates,
    datasets: [
      {
        label: 'temperature',
        data: temps,
        borderColor: 'rgb(255, 99, 132)',
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      }
      ,
      {
        label: 'brightness',
        data: brightness,
        borderColor: 'rgb(53, 162, 235)',
        backgroundColor: 'rgba(53, 162, 235, 0.5)',
      },
    ],
  };

  return (
    <div className="SensorChart">
      <Line options={options} data={data} />
    </div>
  )
}

export default SensorComponent
