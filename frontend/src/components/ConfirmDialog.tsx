import React from 'react';
import {AlertCircle} from 'lucide-react';

// Confirm Dialog Component
const ConfirmDialog = ({ isOpen, onClose, onConfirm, title, message, confirmText, cancelText, type = 'warning' } : any) => {
    if (!isOpen) return null;

    const typeColors : any = {
        warning: 'bg-yellow-100 text-yellow-800',
        danger: 'bg-red-100 text-red-800',
        success: 'bg-green-100 text-green-800',
        info: 'bg-blue-100 text-blue-800'
    };

    return (
        <div className="fixed inset-0 w-full h-full bg-black/50 z-50">
            <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 p-5 bg-white text-black rounded-lg shadow-xl max-w-md w-full">
                <div className="p-6">
                    <div className="flex items-start gap-4">
                        <div className={`p-2 rounded-full ${typeColors[type]}`}>
                            <AlertCircle className="w-6 h-6" />
                        </div>
                        <div className="flex-1">
                            <h3 className="text-lg font-semibold text-gray-900 mb-2">{title}</h3>
                            <p className="text-gray-600">{message}</p>
                        </div>
                    </div>
                    <div className="flex gap-3 mt-6">
                        <button
                            onClick={onClose}
                            className="flex-1 px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-50 font-medium text-gray-700 transition-colors"
                        >
                            {cancelText || 'Hủy'}
                        </button>
                        <button
                            onClick={() => {
                                onConfirm();
                                onClose();
                            }}
                            className={`flex-1 px-4 py-2 rounded-lg font-medium text-white transition-colors ${
                                type === 'danger' ? 'bg-red-600 hover:bg-red-700' : 'bg-blue-600 hover:bg-blue-700'
                            }`}
                        >
                            {confirmText || 'Xác nhận'}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ConfirmDialog