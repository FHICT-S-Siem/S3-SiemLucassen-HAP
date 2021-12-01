import './App.css';
// import Profile from './components/Profile';
import { useAuth0 } from '@auth0/auth0-react';
import { BrowserRouter as Router } from 'react-router-dom';
import { HAPRoutes } from './routes';
import NavBar from './components/Navbar/Navbar';
import SideBar from './components/Sidebar/Sidebar';

function App() {
  const {isLoading} = useAuth0();
  if (isLoading) return <div>Loading...</div>

  return (
    <div className="App">
      <Router>
          <NavBar />
          <div className="wrap">
            <SideBar/>
            <HAPRoutes/>
          </div>
      </Router>
    </div>
  );

}

export default App;
