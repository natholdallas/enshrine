package github.natholdallas.enshrine.common

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/* Class */

typealias RestCtl = RestController

typealias RestAdvice = RestControllerAdvice

typealias Ctl = Controller

typealias Advice = ControllerAdvice

/* RequestMapping */

typealias Get = GetMapping

typealias Post = PostMapping

typealias Put = PutMapping

typealias Delete = DeleteMapping

typealias Patch = PatchMapping

/* Method Arguments */

typealias Body = RequestBody

typealias Param = RequestParam

typealias Attr = ModelAttribute

typealias Path = PathVariable
