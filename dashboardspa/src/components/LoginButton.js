import React from 'react'
import { useAuth0 } from '@auth0/auth0-react'
import './Button.css'

export default function LoginButton() {
    const {loginWithRedirect, isAuthenticated} = useAuth0();
    return (
        !isAuthenticated && (
            <button 
            className={`btn btn--primary btn--medium`} 
            onClick={() => loginWithRedirect()}>
                Login      
            </button>
        )
    )
}