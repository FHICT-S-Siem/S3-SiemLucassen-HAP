import './App.css';
// import Profile from './components/Profile';
import { useAuth0 } from '@auth0/auth0-react';
import { BrowserRouter as Router } from 'react-router-dom';
import { HAPRoutes } from './routes';
import NavBar from './components/Navbar/Navbar';
import Room from './components/Room'

function App() {
  const {isLoading} = useAuth0();
  if (isLoading) return <div>Loading...</div>

  return (
    <>
      <Router>
        <NavBar />
        <Room />
        <HAPRoutes />
      </Router>
    </>
  );

}

export default App;
