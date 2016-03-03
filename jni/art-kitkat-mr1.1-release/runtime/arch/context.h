/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef ART_RUNTIME_ARCH_CONTEXT_H_
#define ART_RUNTIME_ARCH_CONTEXT_H_

#include <stddef.h>
#include <stdint.h>

namespace art {

class StackVisitor;

// Representation of a thread's context on the executing machine, used to implement long jumps in
// the quick stack frame layout.
class Context {
 public:
  // Creates a context for the running architecture
  static Context* Create();

  virtual ~Context() {}

  // Re-initializes the registers for context re-use.
  virtual void Reset() = 0;

  // Read values from callee saves in the given frame. The frame also holds
  // the method that holds the layout.
  virtual void FillCalleeSaves(const StackVisitor& fr) = 0;

  // Set the stack pointer value
  virtual void SetSP(uintptr_t new_sp) = 0;

  // Set the program counter value
  virtual void SetPC(uintptr_t new_pc) = 0;

  // Read the given GPR
  virtual uintptr_t GetGPR(uint32_t reg) = 0;

  // Set the given GPR.
  virtual void SetGPR(uint32_t reg, uintptr_t value) = 0;

  // Smash the caller save registers. If we're throwing, we don't want to return bogus values.
  virtual void SmashCallerSaves() = 0;

  // Switch execution of the executing context to this context
  virtual void DoLongJump() = 0;

 protected:
  enum {
    kBadGprBase = 0xebad6070,
    kBadFprBase = 0xebad8070,
  };
};

}  // namespace art

#endif  // ART_RUNTIME_ARCH_CONTEXT_H_
