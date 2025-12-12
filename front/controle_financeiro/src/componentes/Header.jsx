import React, { useState } from "react";
import { ChevronDown, User, Settings, LogOut } from "lucide-react";


export function Header({ setActiveItem }) {
  const [isProfileOpen, setIsProfileOpen] = useState(false);

  const user = {
    name: 'João Silva',
    email: 'joao.silva@email.com',
    avatar: 'JS'
  };

  return (
    <header className="h-16 bg-white border-b border-gray-200 flex items-center justify-end px-8">
      <div className="relative">
        <button
          onClick={() => setIsProfileOpen(!isProfileOpen)}
          className="flex items-center gap-3 hover:bg-gray-50 rounded-lg px-3 py-2 transition-colors"
        >
          <div className="text-right hidden sm:block">
            <p className="text-sm font-medium text-gray-900">{user.name}</p>
            <p className="text-xs text-gray-500">{user.email}</p>
          </div>
          <div className="w-10 h-10 bg-gradient-to-br from-emerald-500 to-teal-600 rounded-full flex items-center justify-center">
            <span className="text-white font-semibold text-sm">{user.avatar}</span>
          </div>
          <ChevronDown className={`w-4 h-4 text-gray-400 transition-transform ${isProfileOpen ? 'rotate-180' : ''}`} />
        </button>

        {/* Dropdown Menu */}
        {isProfileOpen && (
          <div className="absolute right-0 mt-2 w-64 bg-white rounded-lg shadow-lg border border-gray-200 py-2 z-50">
            <div className="px-4 py-3 border-b border-gray-100">
              <p className="text-sm font-medium text-gray-900">{user.name}</p>
              <p className="text-xs text-gray-500">{user.email}</p>
            </div>
            <button
              onClick={() => {
                setActiveItem('profile');
                setIsProfileOpen(false);
              }}
              className="w-full flex items-center gap-3 px-4 py-2.5 hover:bg-gray-50 transition-colors text-left"
            >
              <User className="w-4 h-4 text-gray-500" />
              <span className="text-sm text-gray-700">Meu Perfil</span>
            </button>
            <button className="w-full flex items-center gap-3 px-4 py-2.5 hover:bg-gray-50 transition-colors text-left">
              <Settings className="w-4 h-4 text-gray-500" />
              <span className="text-sm text-gray-700">Configurações</span>
            </button>
            <div className="border-t border-gray-100 mt-2 pt-2">
              <button className="w-full flex items-center gap-3 px-4 py-2.5 hover:bg-red-50 transition-colors text-left">
                <LogOut className="w-4 h-4 text-red-500" />
                <span className="text-sm text-red-600">Sair</span>
              </button>
            </div>
          </div>
        )}
      </div>
    </header>
  );
}
export default Header;