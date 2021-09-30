import './App.css';
import LoginButton from './components/LoginButton';
// import Profile from './components/Profile';
import { useAuth0 } from '@auth0/auth0-react';
import Topbar from './components/Topbar';
import Sidebar from './components/Sidebar';
import Home from './components/Home';


function App() {
  const {isLoading} = useAuth0();
  if (isLoading) return <div>Loading...</div>

  return (
    <>
      <Topbar />
      <div className="container">
        <Sidebar />
        <div className="content">
          <Home />
          <LoginButton />
        </div>
      </div>
      
      {/* <Profile /> */}
    </>
  );

}

export default App;
