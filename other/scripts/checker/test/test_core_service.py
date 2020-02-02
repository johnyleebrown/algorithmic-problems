# pp -m unittest test_core_service -v

from unittest import TestCase
from core_service import CoreService
import util as u


class TestCoreService(TestCase):

    def test_get_random_problem(self):

        """... check if for the last n problems there is no randomly generated one"""

        test_output_file = 'test_output.txt'
        with CoreService() as core:

            for i in range(1):
                for topic in core.topics_name_list:

                    # get problem list for a topic
                    problem_list = core.topic_to_problem_list_map[topic]
                    problem_list_count = len(problem_list)

                    # generate random problem problem_list_count times
                    for k in range(problem_list_count):

                        # read all results each time
                        results = u.read_from_file(test_output_file)

                        # get rand problem
                        rand_problem = core.get_random_problem(topic, results)

                        # get all problem names
                        generated_problems = [x[1] for x in [result.split(',') for result in results]]
                        x = len(generated_problems)

                        while x % problem_list_count > 0:

                            # check if for the last problem_list_count there was no such problem
                            # i.e randomly generated
                            self.assertTrue(generated_problems[x - 1] != rand_problem)
                            x -= 1

                        generated_problems.append(rand_problem)
                        u.add_line_to_file(test_output_file, topic + ',' + rand_problem)

        u.clear_file(test_output_file)
