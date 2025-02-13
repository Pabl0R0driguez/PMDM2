import React, { createContext, useState, useEffect } from "react";
import { auth, generateUserDocument } from "../firebase";

// Crear el contexto para el usuario
export const UserContext = createContext({ user: null });

const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const unsubscribe = auth.onAuthStateChanged(async (userAuth) => {
      const user = await generateUserDocument(userAuth);
      setUser(user);
    });

    // Limpiar el suscriptor cuando el componente se desmonte
    return () => unsubscribe();
  }, []);

  return (
    <UserContext.Provider value={user}>
      {children}
    </UserContext.Provider>
  );
};

export default UserProvider;
