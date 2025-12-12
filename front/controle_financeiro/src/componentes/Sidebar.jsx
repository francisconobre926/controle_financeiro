import {
    LayoutDashboard,
    Plus,
    ListChecks,
    BarChart3,
    ChevronLeft,
    Menu
} from "lucide-react";
import React, { useState } from "react";
import { ChevronDown, User, Settings, LogOut } from "lucide-react";


export function Sidebar({ isCollapsed, setIsCollapsed, activeItem, setActiveItem }) {
    const menuItems = [
        {
            id: 'dashboard',
            label: 'Dashboard',
            icon: LayoutDashboard,
        },
        {
            id: 'add-transaction',
            label: 'Adicionar Transação',
            icon: Plus,
        },
        {
            id: 'transactions',
            label: 'Minhas Transações',
            icon: ListChecks,
        },
        {
            id: 'reports',
            label: 'Relatórios',
            icon: BarChart3,
        },
    ];

    return (
        <aside
            className={`${isCollapsed ? 'w-20' : 'w-72'
                } bg-white border-r border-gray-200 transition-all duration-300 ease-in-out flex flex-col`}
        >
            {/* Header */}
            <div className="h-16 border-b border-gray-200 flex items-center justify-between px-6">
                {!isCollapsed && (
                    <div className="flex items-center gap-3">
                        <div className="w-8 h-8 bg-gradient-to-br from-emerald-500 to-teal-600 rounded-lg flex items-center justify-center">
                            <span className="text-white font-bold text-sm">F</span>
                        </div>
                        <span className="font-semibold text-gray-900 text-lg">FinControl</span>
                    </div>
                )}
                <button
                    onClick={() => setIsCollapsed(!isCollapsed)}
                    className="p-2 hover:bg-gray-100 rounded-lg transition-colors"
                >
                    {isCollapsed ? (
                        <Menu className="w-5 h-5 text-gray-600" />
                    ) : (
                        <ChevronLeft className="w-5 h-5 text-gray-600" />
                    )}
                </button>
            </div>

            {/* Navigation */}
            <nav className="flex-1 px-4 py-6">
                <ul className="space-y-2">
                    {menuItems.map((item) => {
                        const Icon = item.icon;
                        const isActive = activeItem === item.id;

                        return (
                            <li key={item.id}>
                                <button
                                    onClick={() => setActiveItem(item.id)}
                                    className={`w-full flex items-center gap-3 px-4 py-3 rounded-lg transition-all duration-200 ${isActive
                                            ? 'bg-emerald-50 text-emerald-700 font-medium'
                                            : 'text-gray-600 hover:bg-gray-50 hover:text-gray-900'
                                        }`}
                                >
                                    <Icon
                                        className={`w-5 h-5 flex-shrink-0 ${isActive ? 'text-emerald-600' : 'text-gray-400'
                                            }`}
                                    />
                                    {!isCollapsed && (
                                        <span className="text-sm whitespace-nowrap">{item.label}</span>
                                    )}
                                </button>
                            </li>
                        );
                    })}
                </ul>
            </nav>

            {/* Footer */}
            {!isCollapsed && (
                <div className="p-4 border-t border-gray-200">
                    <div className="bg-gradient-to-br from-emerald-500 to-teal-600 rounded-lg p-4 text-white">
                        <p className="text-sm font-medium mb-1">Dica do Dia</p>
                        <p className="text-xs opacity-90">
                            Organize suas finanças para alcançar seus objetivos!
                        </p>
                    </div>
                </div>
            )}
        </aside>
    );
}
export default Sidebar;