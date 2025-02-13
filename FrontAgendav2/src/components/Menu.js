import React, { useState, useContext } from "react";
import "../styles/Menu.css";
import { UserContext } from "../provider/UserProvider"; // Importamos el contexto de usuario
import desplegar from "../assets/desplegar.png"; // Icono desplegable
import { useHistory } from "react-router-dom"; // Importa useHistory
import defaultImage from "../assets/usuario.png"; // Imagen predeterminada si no hay imagen

const MenuButton = ({ name, img, onClick }) => {
  return (
    <button onClick={onClick}>
      {img && <img src={img} alt={name} style={{ width: "24px", height: "24px" }} />}
      {name}
    </button>
  );
};

export const Dropdown1 = ({ items }) => {
  const [isOpen, setIsOpen] = useState(false);
  const history = useHistory(); // Crea una instancia de useHistory
  const user = useContext(UserContext); // Obtenemos el usuario desde el contexto
  
  // Manejar clic para redirigir
  const handleClick = (path) => {
    history.push(path); // Navega a la ruta proporcionada
  };

  return (
    <div className={`dropdown-1 ${isOpen ? "open" : ""}`}>
      {/* Si el usuario ha iniciado sesión, mostramos su imagen, si no, mostramos una imagen por defecto */}
      <img
        src={user?.photoURL || defaultImage} // Usamos la URL de la imagen del usuario o una imagen predeterminada
        alt="User Icon"
        style={{ width: "24px", height: "24px" }}
      />

      <button onClick={() => setIsOpen(!isOpen)}>
        <span className="material-symbols-outlined"></span>
        {user?.displayName || "Pablo"} {/* Muestra el nombre del usuario si está disponible */}
        <span className="chevron material-symbols-outlined">
          <img src={desplegar} alt="Login Icon" style={{ width: "24px", height: "24px" }} />
        </span>
      </button>

      <div className="dropdown-1-menu">
        <div className="menu-inner">
          <div className="main-menu">
            {items.map((item) => (
              <MenuButton
                key={item.name}
                name={item.displayName || item.name}
                img={item.img}
                onClick={() => handleClick(item.path)} // Llama a handleClick con la ruta
              />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};
