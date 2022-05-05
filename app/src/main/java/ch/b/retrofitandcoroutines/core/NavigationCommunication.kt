package ch.b.retrofitandcoroutines.core

interface NavigationCommunication : Communication<Int>{
    class Base : Communication.Base<Int>(),NavigationCommunication
}