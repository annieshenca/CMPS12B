cmake_minimum_required(VERSION 3.6)
project(Lab4)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp example1.cpp example1.h)
add_executable(Lab4 ${SOURCE_FILES})