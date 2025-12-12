import React, { useState } from 'react';
import { LayoutDashboard, Plus, ListChecks, BarChart3, ChevronLeft, Menu, User, Settings, LogOut, ChevronDown, TrendingUp, TrendingDown, Wallet, CreditCard } from 'lucide-react';

import Sidebar from './componentes/SideBar';
import Header from './componentes/Header';
import { Dashboard } from './pages/dashboard';

function App() {
  const [isCollapsed, setIsCollapsed] = useState(false);
  const [activeItem, setActiveItem] = useState('dashboard');

  const renderContent = () => {
    switch (activeItem) {
      case 'dashboard':
        return <Dashboard />;
      case 'profile':
        return (
          <div className="p-8 bg-gray-50">
            <h1 className="text-3xl font-bold text-gray-900 mb-2">Meu Perfil</h1>
            <p className="text-gray-500">Configurações do perfil apareceriam aqui</p>
          </div>
        );
      default:
        return (
          <div className="p-8 bg-gray-50">
            <h1 className="text-3xl font-bold text-gray-900 mb-2">
              {activeItem.split('-').map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' ')}
            </h1>
            <p className="text-gray-500">Conteúdo da seção selecionada apareceria aqui</p>
          </div>
        );
    }
  };

  return (
    <div className="flex h-screen bg-gray-50">
      <Sidebar 
        isCollapsed={isCollapsed}
        setIsCollapsed={setIsCollapsed}
        activeItem={activeItem}
        setActiveItem={setActiveItem}
      />
      <main className="flex-1 flex flex-col overflow-hidden">
        <Header setActiveItem={setActiveItem} />
        <div className="flex-1 overflow-auto">
          {renderContent()}
        </div>
      </main>
    </div>
  );
}

export default App;