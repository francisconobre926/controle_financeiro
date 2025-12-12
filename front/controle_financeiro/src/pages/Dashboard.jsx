import React, { useState } from 'react';
import { LayoutDashboard, Plus, ListChecks, BarChart3, ChevronLeft, Menu, User, Settings, LogOut, ChevronDown, TrendingUp, TrendingDown, Wallet, CreditCard } from 'lucide-react';


// ==================== Dashboard.jsx ====================
export function Dashboard() {
  const stats = [
    {
      title: 'Saldo Total',
      value: 'R$ 15.430,00',
      icon: Wallet,
      change: '+12.5%',
      positive: true,
      bgColor: 'bg-emerald-50',
      iconColor: 'text-emerald-600'
    },
    {
      title: 'Receitas',
      value: 'R$ 8.240,00',
      icon: TrendingUp,
      change: '+8.2%',
      positive: true,
      bgColor: 'bg-blue-50',
      iconColor: 'text-blue-600'
    },
    {
      title: 'Despesas',
      value: 'R$ 3.890,00',
      icon: TrendingDown,
      change: '+3.1%',
      positive: false,
      bgColor: 'bg-red-50',
      iconColor: 'text-red-600'
    },
    {
      title: 'Cartão de Crédito',
      value: 'R$ 1.250,00',
      icon: CreditCard,
      change: '-15.3%',
      positive: true,
      bgColor: 'bg-purple-50',
      iconColor: 'text-purple-600'
    }
  ];

  const recentTransactions = [
    { id: 1, description: 'Salário', amount: 'R$ 5.000,00', type: 'income', date: '10/12/2025' },
    { id: 2, description: 'Supermercado', amount: 'R$ 450,00', type: 'expense', date: '09/12/2025' },
    { id: 3, description: 'Freelance', amount: 'R$ 1.200,00', type: 'income', date: '08/12/2025' },
    { id: 4, description: 'Conta de Luz', amount: 'R$ 180,00', type: 'expense', date: '07/12/2025' },
    { id: 5, description: 'Internet', amount: 'R$ 99,90', type: 'expense', date: '06/12/2025' }
  ];

  return (
    <div className="p-8 overflow-auto bg-gray-50">
      <div className="max-w-7xl mx-auto">
        {/* Title */}
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900 mb-2">Dashboard</h1>
          <p className="text-gray-500">Visão geral das suas finanças</p>
        </div>

        {/* Stats Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {stats.map((stat, index) => {
            const Icon = stat.icon;
            return (
              <div key={index} className="bg-white rounded-lg p-6 shadow-sm border border-gray-200">
                <div className="flex items-center justify-between mb-4">
                  <div className={`${stat.bgColor} p-3 rounded-lg`}>
                    <Icon className={`w-6 h-6 ${stat.iconColor}`} />
                  </div>
                  <span className={`text-sm font-medium ${stat.positive ? 'text-green-600' : 'text-red-600'}`}>
                    {stat.change}
                  </span>
                </div>
                <p className="text-gray-500 text-sm mb-1">{stat.title}</p>
                <p className="text-2xl font-bold text-gray-900">{stat.value}</p>
              </div>
            );
          })}
        </div>

        {/* Recent Transactions */}
        <div className="bg-white rounded-lg shadow-sm border border-gray-200">
          <div className="p-6 border-b border-gray-200">
            <h2 className="text-xl font-semibold text-gray-900">Transações Recentes</h2>
          </div>
          <div className="divide-y divide-gray-200">
            {recentTransactions.map((transaction) => (
              <div key={transaction.id} className="p-6 flex items-center justify-between hover:bg-gray-50 transition-colors">
                <div className="flex items-center gap-4">
                  <div className={`w-10 h-10 rounded-full flex items-center justify-center ${
                    transaction.type === 'income' ? 'bg-green-100' : 'bg-red-100'
                  }`}>
                    {transaction.type === 'income' ? (
                      <TrendingUp className="w-5 h-5 text-green-600" />
                    ) : (
                      <TrendingDown className="w-5 h-5 text-red-600" />
                    )}
                  </div>
                  <div>
                    <p className="font-medium text-gray-900">{transaction.description}</p>
                    <p className="text-sm text-gray-500">{transaction.date}</p>
                  </div>
                </div>
                <p className={`font-semibold ${
                  transaction.type === 'income' ? 'text-green-600' : 'text-red-600'
                }`}>
                  {transaction.type === 'income' ? '+' : '-'} {transaction.amount}
                </p>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

// ==================== App.jsx ====================
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