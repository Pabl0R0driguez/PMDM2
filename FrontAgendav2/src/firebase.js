import { initializeApp } from "firebase/app";
import { getAuth, GoogleAuthProvider, signInWithPopup, signInWithEmailAndPassword } from "firebase/auth";
import { getFirestore, doc, getDoc, setDoc } from "firebase/firestore"; // Firestore

// Configuración de Firebase
const firebaseConfig = {
  apiKey: "AIzaSyBbMgMiFEvuerhGK1f8L-sHNrRE4ZVF6KQ",
  authDomain: "loginproyectofinal-f76cb.firebaseapp.com",
  projectId: "loginproyectofinal-f76cb",
  storageBucket: "loginproyectofinal-f76cb.firebasestorage.app",
  messagingSenderId: "991930152122",
  appId: "1:991930152122:web:fdf48ac05af4fa40067e83",
  measurementId: "G-41K341S8RX"
};

// Inicializar Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const db = getFirestore(app); // Inicializar Firestore
const googleProvider = new GoogleAuthProvider();

// Función para iniciar sesión con Google
const signInWithGoogle = () => {
  return signInWithPopup(auth, googleProvider);
};

// Función para iniciar sesión con email y contraseña
const signInWithEmail = (email, password) => {
  return signInWithEmailAndPassword(auth, email, password);
};

// Actualización en generateUserDocument
const generateUserDocument = async (userAuth) => {
  if (!userAuth) return null;

  const userRef = doc(db, "users", userAuth.uid);
  const userSnap = await getDoc(userRef);

  // Si el documento no existe en Firestore, lo creamos
  if (!userSnap.exists()) {
    const { email, displayName, photoURL } = userAuth;  // Aquí incluimos photoURL
    try {
      // Guardamos la información del usuario, incluyendo photoURL
      await setDoc(userRef, { displayName, email, photoURL });
    } catch (error) {
      console.error("Error creando usuario en Firestore", error);
    }
  }

  // Retornamos los datos del usuario, incluyendo photoURL
  return userSnap.data();
};


// Exportar las funciones necesarias
export { auth, signInWithGoogle, signInWithEmail, generateUserDocument };
